package pengyi.socketserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import java.io.IOException;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Created by pengyi on 2016/3/9.
 */
public class TcpService implements Runnable {

    private ServerSocket serverSocket;
    boolean started = false;
    public static Map<String, Client> userClients = new HashMap<String, Client>();
    public static Map<String, Client> driverClients = new HashMap<String, Client>();
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MessageSource messageSource;

    @Override
    public void run() {
        int port = 8888;
        boolean used = true;
        while (used) {
            try {
                serverSocket = new ServerSocket(port);
                started = true;
                used = false;
                logger.info(messageSource.getMessage("socket.open.success.message", new Object[]{port}, Locale.CHINA));
            } catch (BindException e) {
                port++;
            } catch (IOException e) {
                logger.error("socket.open.fail.message");
                e.printStackTrace();
                used = false;
            }
        }

        try {
            while (started) {
                Socket s = serverSocket.accept();
                new Client(s);
            }
        } catch (IOException e) {
            logger.error("socket.server.dirty.shutdown.message");
            e.printStackTrace();
        } finally {
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
