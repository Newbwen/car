package pengyi.application.moneydetailed.command;

import pengyi.core.commons.command.BasicPaginationCommand;

/**
 * Created by YJH on 2016/3/9.
 */
public class ListMoneyDetailedCommand extends BasicPaginationCommand {

    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
