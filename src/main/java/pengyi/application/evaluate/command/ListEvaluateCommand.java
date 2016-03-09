package pengyi.application.evaluate.command;

import pengyi.core.commons.command.BasicPaginationCommand;

/**
 * Created by ${lvdi} on 2016/3/8.
 */
public class ListEvaluateCommand extends BasicPaginationCommand {

    private String evaluateUserId;                  //评价人
    private String orderId;                         //订单


    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getEvaluateUserId() {
        return evaluateUserId;
    }

    public void setEvaluateUserId(String evaluateUserId) {
        this.evaluateUserId = evaluateUserId;
    }



}
