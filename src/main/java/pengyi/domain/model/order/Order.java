package pengyi.domain.model.order;

import pengyi.domain.model.base.Identity;
import pengyi.domain.model.report.Report;
import pengyi.domain.model.user.BaseUser;

import java.math.BigDecimal;

/**
 * 订单
 * Created by pengyi on 2016/3/4.
 */
public class Order extends Identity{

    private String orderNumber;                         //订单号
    private BaseUser orderUser;                         //下单人
    private String createDate;                          //下单时间
    private BaseUser receiveUser;                       //接单人
    private String receiveDate;                         //接单时间
    private String subscribeDate;                       //预约时间
    private String beginTime;                           //开始时间
    private int type;                                   //类型（1代驾、2专车、3出租车）
    private String endTime;                             //订单完成时间
    private BigDecimal shouldMoney;                     //应付
    private BigDecimal extraMoney;                      //调度费
    private String payTime;                             //支付时间
    private int orderStatus;                            //订单状态（1已下单、2已接单、3已开始、4已结束待支付、5已完成）
    private int evaluateStatus;                         //评价状态（0未评价、1司机已评价、2用户已评价、3已评价）

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public BaseUser getOrderUser() {
        return orderUser;
    }

    public void setOrderUser(BaseUser orderUser) {
        this.orderUser = orderUser;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public BaseUser getReceiveUser() {
        return receiveUser;
    }

    public void setReceiveUser(BaseUser receiveUser) {
        this.receiveUser = receiveUser;
    }

    public String getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(String receiveDate) {
        this.receiveDate = receiveDate;
    }

    public String getSubscribeDate() {
        return subscribeDate;
    }

    public void setSubscribeDate(String subscribeDate) {
        this.subscribeDate = subscribeDate;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
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

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    public int getEvaluateStatus() {
        return evaluateStatus;
    }

    public void setEvaluateStatus(int evaluateStatus) {
        this.evaluateStatus = evaluateStatus;
    }

    public Order() {
        super();
    }

    public Order(String orderNumber, BaseUser orderUser, String createDate, BaseUser receiveUser, String receiveDate, String subscribeDate, String beginTime, int type, String endTime, BigDecimal shouldMoney, BigDecimal extraMoney, String payTime, int orderStatus, int evaluateStatus) {
        super();
        this.orderNumber = orderNumber;
        this.orderUser = orderUser;
        this.createDate = createDate;
        this.receiveUser = receiveUser;
        this.receiveDate = receiveDate;
        this.subscribeDate = subscribeDate;
        this.beginTime = beginTime;
        this.type = type;
        this.endTime = endTime;
        this.shouldMoney = shouldMoney;
        this.extraMoney = extraMoney;
        this.payTime = payTime;
        this.orderStatus = orderStatus;
        this.evaluateStatus = evaluateStatus;
    }
}
