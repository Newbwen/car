package pengyi.socketserver;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import pengyi.core.type.UserType;
import pengyi.socketserver.model.ReceiveObj;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.util.Locale;

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
    private boolean bConnected = false;

    @Autowired
    private MessageSource messageSource;

    public Client(Socket s) {
        this.s = s;
        try {
            dis = new DataInputStream(s.getInputStream());
            dos = new DataOutputStream(s.getOutputStream());
            bConnected = true;
        } catch (IOException e) {
            logger.info(messageSource.getMessage("socket.connection.fail.message", new Object[]{"unknow", "UNKNOW"}, Locale.CHINA));
            close();
            e.printStackTrace();
        }
    }

    public void send(String str) {
        try {
            dos.writeUTF(str);
        } catch (IOException e) {
            logger.info(messageSource.getMessage("socket.server.sendMessage.fail.message", new Object[]{phone, userType}, Locale.CHINA));
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

    public void run() {
        try {
            while (bConnected) {
                String str = dis.readUTF();
                dos.writeUTF(str);
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
            logger.info(messageSource.getMessage("socket.shutdown.message", new Object[]{phone, userType}, Locale.CHINA));
            e.printStackTrace();
        } catch (IOException e) {
            logger.info(messageSource.getMessage("socket.dirty.shutdown.message", new Object[]{phone, userType}, Locale.CHINA));
            e.printStackTrace();
        } finally {
            close();
        }
    }
}