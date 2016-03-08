package pengyi.domain.model.report;

import pengyi.core.type.StatusType;
import pengyi.domain.model.base.Identity;
import pengyi.domain.model.order.Order;
import pengyi.domain.model.user.BaseUser;

/**
 * 举报
 * Created by pengyi on 2016/3/4.
 * update by liubowen on 2016/3/7 添加开始处理时间，处理完成时间，举报时间
 */
public class Report extends Identity {

    private BaseUser reportUser;                //举报人
    private Order order;                        //举报订单
    private String reportTime;                  //举报时间
    private String startDealTime;               //开始处理时间
    private String endDealTime;                 //处理完成时间
    private String description;                 //说明
    private StatusType status;                  //状态（待处理、处理中、处理完成）

    public BaseUser getReportUser() {
        return reportUser;
    }

    public void setReportUser(BaseUser reportUser) {
        this.reportUser = reportUser;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public String getReportTime() {
        return reportTime;
    }

    public void setReportTime(String reportTime) {
        this.reportTime = reportTime;
    }

    public String getStartDealTime() {
        return startDealTime;
    }

    public void setStartDealTime(String startDealTime) {
        this.startDealTime = startDealTime;
    }

    public String getEndDealTime() {
        return endDealTime;
    }

    public void setEndDealTime(String endDealTime) {
        this.endDealTime = endDealTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public StatusType getStatus() {
        return status;
    }

    public void setStatus(StatusType status) {
        this.status = status;
    }

    public Report() {
        super();
    }

    public Report(BaseUser reportUser, Order order, String reportTime, String startDealTime, String endDealTime, String description, StatusType status) {
        super();
        this.reportUser = reportUser;
        this.order = order;
        this.reportTime = reportTime;
        this.startDealTime = startDealTime;
        this.endDealTime = endDealTime;
        this.description = description;
        this.status = status;
    }
}
