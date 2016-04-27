import org.testng.annotations.Test;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by pengyi on 2016/3/10.
 */
public class TcpServiceTest {


    @Test
    public void test(){
        try {
            Socket socket = new Socket("211.149.234.36", 8888);
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
