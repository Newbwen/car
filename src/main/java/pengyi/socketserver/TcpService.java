package pengyi.socketserver;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by pengyi on 2016/3/9.
 */
public class TcpService implements Runnable {

    private ServerSocket serverSocket;
    boolean started = false;
    Map<String, Client> clients = new HashMap<String, Client>();

    @Override
    public void run() {
        int port = 8888;
        boolean used = true;
        while (used) {
            try {
                serverSocket = new ServerSocket(port);
                started = true;
                used = false;
            } catch (BindException e) {
                port++;
            } catch (IOException e) {
                e.printStackTrace();
                used = false;
            }
        }

        try {
            while (started) {
                Socket s = serverSocket.accept();
                Client c = new Client(s);
                new Thread(c).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    class Client implements Runnable {
        private Socket s;
        private String userId;
        private DataInputStream dis = null;
        private DataOutputStream dos = null;
        private boolean bConnected = false;

        public Client(Socket s) {
            this.s = s;
            try {
                dis = new DataInputStream(s.getInputStream());
                dos = new DataOutputStream(s.getOutputStream());
                bConnected = true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void send(String str) {
            try {
                dos.writeUTF(str);
            } catch (IOException e) {
                clients.remove(userId);
            }
        }

        public void run() {
            try {
                while (bConnected) {
                    String str = dis.readUTF();
                    dos.writeUTF(str);
//                    ReceiveObj obj = JSON.parseObject(str, ReceiveObj.class);
//                    switch (obj.getType()) {
//                        case 1:
//                            userId = obj.getContent();
//                            clients.put(userId, this);
//                            break;
//                    }
                }
            } catch (EOFException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                clients.remove(userId);
                try {
                    if (dis != null)
                        dis.close();
                    if (dos != null)
                        dos.close();
                    if (s != null) {
                        s.close();
                    }

                } catch (IOException e1) {
                    e1.printStackTrace();
                }

            }
        }
    }
}
