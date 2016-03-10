package pengyi.application.moneydetailed.command;

import pengyi.core.commons.command.BasicPaginationCommand;

/**
 * Created by YJH on 2016/3/9.
 */
public class ListMoneyDetailedCommand extends BasicPaginationCommand {

    private String phone;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
