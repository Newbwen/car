package pengyi.application.message.command;

import pengyi.core.commons.command.BasicPaginationCommand;
import pengyi.core.type.ShowType;

import javax.xml.crypto.Data;
import java.util.Date;

/**
 * Created by liubowen on 2016/3/21.
 */
public class CompanyListMessageCommand extends BasicPaginationCommand {
    private String receiveBaseUser;             //接收人
    private Date beginTime;
    private Date endTime;
    private String content;                 //内容
    private ShowType showType;              //是否显示
    private String company;

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
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

    public String getReceiveBaseUser() {

        return receiveBaseUser;
    }

    public void setReceiveBaseUser(String receiveBaseUser) {
        this.receiveBaseUser = receiveBaseUser;
    }
}
