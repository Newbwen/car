package pengyi.application.message.command;

import pengyi.core.type.MessageType;
import pengyi.domain.model.user.BaseUser;

/**
 * Created by liubowen on 2016/3/8.
 */
public class CreateMessageCommand {
    private BaseUser sendBaseUser;                  //发送人
    private BaseUser receiveBaseUser;               //接收人
    private String sendDate;                //发送时间
    private String receiveDate;             //接收时间
    private String content;                 //内容
    private MessageType type;                       //类型（0为系统消息）

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

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }
}
