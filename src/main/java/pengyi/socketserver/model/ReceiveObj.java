package pengyi.socketserver.model;

/**
 * 接收客户端
 * Created by pengyi on 2016/3/10.
 */
public class ReceiveObj {

    private int type;               //1为连接服务器，2为下单，3为接单
    private String content;         //类容

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ReceiveObj() {
    }

    public ReceiveObj(int type, String content) {
        this.type = type;
        this.content = content;
    }
}
