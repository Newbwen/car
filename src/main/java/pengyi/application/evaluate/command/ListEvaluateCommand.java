package pengyi.application.evaluate.command;

import pengyi.core.commons.command.BasicPaginationCommand;

/**
 * Created by ${lvdi} on 2016/3/8.
 */
public class ListEvaluateCommand extends BasicPaginationCommand {

    private String evaluateUser;                  //评价人
    private String order;                         //订单

    public String getEvaluateUser() {
        return evaluateUser;
    }

    public void setEvaluateUser(String evaluateUser) {
        this.evaluateUser = evaluateUser;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }
}
