package pengyi.domain.model.report;

import pengyi.domain.model.base.Identity;
import pengyi.domain.model.order.Order;
import pengyi.domain.model.user.BaseUser;

/**
 * 举报
 * Created by pengyi on 2016/3/4.
 */
public class Report extends Identity {

    private BaseUser reportUser;                //举报人
    private Order order;                        //举报订单
    private String description;                 //说明
    private int status;                         //状态（待处理、处理中、处理完成）

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Report() {
        super();
    }

    public Report(BaseUser reportUser, Order order, String description, int status) {
        super();
        this.reportUser = reportUser;
        this.order = order;
        this.description = description;
        this.status = status;
    }
}
