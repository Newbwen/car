package pengyi.socketserver;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import pengyi.core.type.UserType;
import pengyi.socketserver.model.ReceiveObj;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by pengyi on 2016/3/25.
 */
public class Client implements Runnable {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private Socket s;
    private String phone;
    private UserType userType;
    private DataInputStream dis = null;
    private DataOutputStream dos = null;


    public Client(Socket s) {
        this.s = s;
        try {
            dis = new DataInputStream(s.getInputStream());
            dos = new DataOutputStream(s.getOutputStream());
//            String str = dis.readUTF();
//            ReceiveObj obj = JSON.parseObject(str, ReceiveObj.class);
//            phone = obj.getPhone();
//            userType = obj.getType();
//            switch (obj.getType()) {
//                case USER:
//                    TcpService.userClients.put(phone, this);
//                    break;
//                case DRIVER:
//                    TcpService.driverClients.put(phone, this);
//                    break;
//            }
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    try {
//                        dis.readUTF();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                        close();
//                    }
//                }
//            }).start();
        } catch (EOFException e) {
            logger.info("socket.shutdown.message");
            close();
        }  catch (IOException e) {
            logger.info("socket.connection.fail.message");
            close();
        }
    }

    public void send(String str) {
        try {
            dos.writeUTF(str);
        } catch (IOException e) {
            logger.info("socket.server.sendMessage.fail.message");
            close();
        }
    }

    public void close() {
        switch (userType) {
            case USER:
                TcpService.userClients.remove(phone);
                break;
            case DRIVER:
                TcpService.driverClients.remove(phone);
                break;
        }

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

    @Override
    public void run() {
        try {
            while (true) {
                String str = dis.readUTF();
                ReceiveObj obj = JSON.parseObject(str, ReceiveObj.class);
                phone = obj.getPhone();
                userType = obj.getType();
                switch (obj.getType()) {
                    case USER:
                        TcpService.userClients.put(phone, this);
                        break;
                    case DRIVER:
                        TcpService.driverClients.put(phone, this);
                        break;
                }
            }
        } catch (EOFException e) {
            logger.info("socket.shutdown.message");
            close();
        } catch (IOException e) {
            logger.info("socket.dirty.shutdown.message");
            close();
        } finally {
            close();
        }
    }
}