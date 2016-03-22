package pengyi.application.order.command;

import pengyi.core.type.DriverType;
import pengyi.core.type.EvaluateStatus;
import pengyi.core.type.OrderStatus;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by YJH on 2016/3/8.
 */
public class CreateOrderCommand {

    private String orderUser;                         //下单人
    private String subscribeDate;                       //预约时间
    private DriverType driverType;                    //接单司机类型类型
    private BigDecimal extraMoney;                      //调度费

    public String getOrderUser() {
        return orderUser;
    }

    public void setOrderUser(String orderUser) {
        this.orderUser = orderUser;
    }

    public String getSubscribeDate() {
        return subscribeDate;
    }

    public void setSubscribeDate(String subscribeDate) {
        this.subscribeDate = subscribeDate;
    }

    public DriverType getDriverType() {
        return driverType;
    }

    public void setDriverType(DriverType driverType) {
        this.driverType = driverType;
    }

    public BigDecimal getExtraMoney() {
        return extraMoney;
    }

    public void setExtraMoney(BigDecimal extraMoney) {
        this.extraMoney = extraMoney;
    }
}
