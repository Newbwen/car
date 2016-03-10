package pengyi.socketserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by pengyi on 2016/1/14.
 */
class ThreadContainer {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private TcpService service;

    public void start() {
        service = new TcpService();
        service.start();
    }

    public void stop(){

    }
}