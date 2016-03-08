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

    private String orderNumber;                         //订单号
    private String orderUser;                         //下单人
    private String createDate;                          //下单时间
    private String receiveUser;                       //接单人
    private Date receiveDate;                         //接单时间
    private Date subscribeDate;                       //预约时间
    private DriverType driverType;                    //类型
    private BigDecimal shouldMoney;                     //应付
    private BigDecimal extraMoney;                      //调度费

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getOrderUser() {
        return orderUser;
    }

    public void setOrderUser(String orderUser) {
        this.orderUser = orderUser;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getReceiveUser() {
        return receiveUser;
    }

    public void setReceiveUser(String receiveUser) {
        this.receiveUser = receiveUser;
    }

    public Date getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(Date receiveDate) {
        this.receiveDate = receiveDate;
    }

    public Date getSubscribeDate() {
        return subscribeDate;
    }

    public void setSubscribeDate(Date subscribeDate) {
        this.subscribeDate = subscribeDate;
    }

    public DriverType getDriverType() {
        return driverType;
    }

    public void setDriverType(DriverType driverType) {
        this.driverType = driverType;
    }

    public BigDecimal getShouldMoney() {
        return shouldMoney;
    }

    public void setShouldMoney(BigDecimal shouldMoney) {
        this.shouldMoney = shouldMoney;
    }

    public BigDecimal getExtraMoney() {
        return extraMoney;
    }

    public void setExtraMoney(BigDecimal extraMoney) {
        this.extraMoney = extraMoney;
    }
}
