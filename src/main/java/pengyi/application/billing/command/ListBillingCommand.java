package pengyi.application.billing.command;

import pengyi.core.commons.command.BasicPaginationCommand;

/**
 * Created by YJH on 2016/3/21.
 */
public class ListBillingCommand extends BasicPaginationCommand {

    private String company;

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
