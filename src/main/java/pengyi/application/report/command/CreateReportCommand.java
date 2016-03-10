package pengyi.application.report.command;


/**
 * Created by liubowen on 2016/3/10.
 * 页面创建
 */
public class CreateReportCommand {
    private String reportUser;                //举报人
    private String order;                        //举报订单
    private String description;                 //说明

    public String getReportUser() {
        return reportUser;
    }

    public void setReportUser(String reportUser) {
        this.reportUser = reportUser;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
