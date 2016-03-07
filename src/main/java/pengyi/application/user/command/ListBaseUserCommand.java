package pengyi.application.user.command;

import pengyi.core.commons.command.BasicPaginationCommand;

/**
 * Created by YJH on 2016/3/7.
 */
public class ListBaseUserCommand extends BasicPaginationCommand {

    private String phone;
    private Boolean status;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
