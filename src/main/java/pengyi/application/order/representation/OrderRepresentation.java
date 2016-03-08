package pengyi.application.order.representation;

import pengyi.application.user.representation.BaseUserRepresentation;
import pengyi.core.type.DriverType;
import pengyi.core.type.EvaluateStatus;
import pengyi.core.type.OrderStatus;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by YJH on 2016/3/8.
 */
public class OrderRepresentation {

    private String id;
    private Integer version;

    private String orderNumber;                         //订单号
    private BaseUserRepresentation orderUser;                         //下单人
    private Date createDate;                          //下单时间
    private BaseUserRepresentation receiveUser;                       //接单人
    private Date receiveDate;                         //接单时间
    private Date subscribeDate;                       //预约时间
    private Date beginTime;                           //开始时间
    private DriverType driverType;                    //类型
    private Date endTime;                             //订单完成时间
    private BigDecimal shouldMoney;                     //应付
    private BigDecimal extraMoney;                      //调度费
    private Date payTime;                             //支付时间
    private OrderStatus orderStatus;                  //订单状态
    private EvaluateStatus evaluateStatus;            //评价状态

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public BaseUserRepresentation getOrderUser() {
        return orderUser;
    }

    public void setOrderUser(BaseUserRepresentation orderUser) {
        this.orderUser = orderUser;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public BaseUserRepresentation getReceiveUser() {
        return receiveUser;
    }

    public void setReceiveUser(BaseUserRepresentation receiveUser) {
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

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public DriverType getDriverType() {
        return driverType;
    }

    public void setDriverType(DriverType driverType) {
        this.driverType = driverType;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
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

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public EvaluateStatus getEvaluateStatus() {
        return evaluateStatus;
    }

    public void setEvaluateStatus(EvaluateStatus evaluateStatus) {
        this.evaluateStatus = evaluateStatus;
    }
}
