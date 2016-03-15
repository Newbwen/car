package pengyi.application.order.command;

import pengyi.core.commons.command.BasicPaginationCommand;
import pengyi.core.type.OrderStatus;

/**
 * Created by YJH on 2016/3/8.
 */
public class ListOrderCommand extends BasicPaginationCommand {

    private String orderNumber;
    private OrderStatus orderStatus;

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
}
