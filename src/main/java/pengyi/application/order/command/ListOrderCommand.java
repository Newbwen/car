package pengyi.application.order.command;

import pengyi.core.commons.command.BasicPaginationCommand;
import pengyi.core.type.CarType;
import pengyi.core.type.DriverType;
import pengyi.core.type.OrderStatus;

/**
 * Created by YJH on 2016/3/8.
 */
public class ListOrderCommand extends BasicPaginationCommand {

    private String orderNumber;
    private OrderStatus orderStatus;
    private String orderUser;
    private String receiveUser;
    private DriverType driverType;
    private CarType carType;

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

    public String getOrderUser() {
        return orderUser;
    }

    public void setOrderUser(String orderUser) {
        this.orderUser = orderUser;
    }

    public String getReceiveUser() {
        return receiveUser;
    }

    public void setReceiveUser(String receiveUser) {
        this.receiveUser = receiveUser;
    }

    public DriverType getDriverType() {
        return driverType;
    }

    public void setDriverType(DriverType driverType) {
        this.driverType = driverType;
    }

    public CarType getCarType() {
        return carType;
    }

    public void setCarType(CarType carType) {
        this.carType = carType;
    }
}
