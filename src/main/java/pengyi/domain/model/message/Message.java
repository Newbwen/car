package pengyi.domain.model.message;

import pengyi.domain.model.base.Identity;
import pengyi.domain.model.user.BaseUser;

/**
 * 站内信
 * Created by pengyi on 2016/3/4.
 */
public class Message extends Identity {

    private BaseUser sendBaseUser;                  //发送人
    private BaseUser receiveBaseUser;               //接收人
    private String sendDate;                //发送时间
    private String receiveDate;             //接收时间
    private String content;                 //内容
    private int type;                       //类型（0为系统消息）

    public BaseUser getSendBaseUser() {
        return sendBaseUser;
    }

    public void setSendBaseUser(BaseUser sendBaseUser) {
        this.sendBaseUser = sendBaseUser;
    }

    public BaseUser getReceiveBaseUser() {
        return receiveBaseUser;
    }

    public void setReceiveBaseUser(BaseUser receiveBaseUser) {
        this.receiveBaseUser = receiveBaseUser;
    }

    public String getSendDate() {
        return sendDate;
    }

    public void setSendDate(String sendDate) {
        this.sendDate = sendDate;
    }

    public String getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(String receiveDate) {
        this.receiveDate = receiveDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Message() {
        super();
    }

    public Message(BaseUser sendBaseUser, BaseUser receiveBaseUser, String sendDate, String receiveDate, String content, int type) {
        super();
        this.sendBaseUser = sendBaseUser;
        this.receiveBaseUser = receiveBaseUser;
        this.sendDate = sendDate;
        this.receiveDate = receiveDate;
        this.content = content;
        this.type = type;
    }
}
