package pengyi.application.message.command;

import pengyi.core.commons.command.BasicPaginationCommand;
import pengyi.core.type.ShowType;

/**
 * Created by liubowen on 2016/3/8.
 */
public class ListMessageCommand extends BasicPaginationCommand {
    private String sendBaseUser;             //发送人
    private String sendDate;                //发送时间
    private String content;                 //内容
    private ShowType showType;              //是否显示

    public String getSendBaseUser() {
        return sendBaseUser;
    }

    public void setSendBaseUser(String sendBaseUser) {
        this.sendBaseUser = sendBaseUser;
    }


    public String getSendDate() {
        return sendDate;
    }

    public void setSendDate(String sendDate) {
        this.sendDate = sendDate;
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public ShowType getShowType() {
        return showType;
    }

    public void setShowType(ShowType showType) {
        this.showType = showType;
    }
}
