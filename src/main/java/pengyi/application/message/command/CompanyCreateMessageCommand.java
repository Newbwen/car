package pengyi.application.message.command;

import java.util.List;

/**
 * Created by liubowen on 2016/3/21.
 */
public class CompanyCreateMessageCommand {
    private String company;                  //发送人
    private List<String> receiveBaseUser;            //接收人
    private String sendDate;                //发送时间
    private String content;                 //内容

    public List<String> getReceiveBaseUser() {
        return receiveBaseUser;
    }

    public void setReceiveBaseUser(List<String> receiveBaseUser) {
        this.receiveBaseUser = receiveBaseUser;
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



    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
