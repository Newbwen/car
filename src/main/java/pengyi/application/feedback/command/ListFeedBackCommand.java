package pengyi.application.feedback.command;

import pengyi.core.commons.command.BasicPaginationCommand;

/**
 * Created by YJH on 2016/3/9.
 */
public class ListFeedBackCommand extends BasicPaginationCommand {

    private String email;       //联系邮箱
    private String phone;       //联系电话
    private String qq;          //联系QQ

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }
}
