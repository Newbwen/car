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
import java.util.ArrayList;
import java.util.List;

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
        } catch (EOFException e) {
            logger.info("socket.shutdown.message" + phone + userType);
            close();
        } catch (IOException e) {
            logger.info("socket.connection.fail.message" + phone + userType + e.getMessage());
            close();
        }
    }

    public boolean send(String str) {
        try {
            dos.writeUTF(str.replace(" ", "").replace("\n", "").replace("\t", ""));
            logger.info("socket.server.sendMessage.success.message" + phone + userType);
            return true;
        } catch (IOException e) {
            logger.info("socket.server.sendMessage.fail.message" + phone + userType + e.getMessage());
            close();
            return false;
        }
    }

    public void close() {

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
        } finally {
            if (null != userType && null != phone) {
                switch (userType) {
                    case USER:
                        TcpService.userClients.remove(phone);
                        break;
                    case DRIVER:
                        TcpService.driverClients.remove(phone);
                        break;
                }
            }
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
                logger.info("socket.connection.success.message" + phone + userType);
                switch (obj.getType()) {
                    case USER:
                        if (TcpService.userClients.containsKey(phone)) {
                            if (TcpService.userClients.get(phone) != this) {
                                TcpService.userClients.get(phone).send("exit");
                            }
                            TcpService.userClients.remove(phone);
                        }
                        TcpService.userClients.put(phone, this);
                        if (TcpService.userMessages.containsKey(phone)) {
                            List<String> messages = TcpService.userMessages.get(phone);
                            List<String> newMessages = new ArrayList<String>();
                            for (String message : messages) {
                                if (!send(message)) {
                                    newMessages.add(message);
                                }
                            }
                            TcpService.userMessages.remove(phone);
                            TcpService.userMessages.put(phone, newMessages);
                        }
                        break;
                    case DRIVER:
                        if (TcpService.driverClients.containsKey(phone)) {
                            if (TcpService.driverClients.get(phone) != this) {
                                TcpService.driverClients.get(phone).send("exit");
                            }
                            TcpService.driverClients.remove(phone);
                        }
                        TcpService.driverClients.put(phone, this);
                        if (TcpService.driverMessages.containsKey(phone)) {
                            List<String> messages = TcpService.driverMessages.get(phone);
                            List<String> newMessages = new ArrayList<String>();
                            for (String message : messages) {
                                if (!send(message)) {
                                    newMessages.add(message);
                                }
                            }
                            TcpService.driverMessages.remove(phone);
                            TcpService.driverMessages.put(phone, newMessages);
                        }
                        break;
                }
            }
        } catch (EOFException e) {
            logger.info("socket.shutdown.message" + phone + userType);
        } catch (IOException e) {
            logger.info("socket.dirty.shutdown.message" + phone + userType + e.getMessage());
        } finally {
            close();
        }
    }
}