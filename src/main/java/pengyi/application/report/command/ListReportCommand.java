package pengyi.application.report.command;

import pengyi.application.order.representation.OrderRepresentation;
import pengyi.application.user.user.representation.UserRepresentation;
import pengyi.core.commons.command.BasicPaginationCommand;
import pengyi.core.type.ReportStatus;

/**
 * Created by liubowen on 2016/3/9.
 */
public class ListReportCommand extends BasicPaginationCommand {
    private String reportUser;      //举报人
    private String order;          //举报订单
    private String reportTime;                  //举报时间
    private String  startDealTime;               //开始处理时间
    private String endDealTime;                 //处理完成时间
    private String description;                 //说明
    private ReportStatus status;                //状态（待处理、处理中、处理完成）
    private String handleResult;                   //处理结果

    public String getHandleResult() {
        return handleResult;
    }

    public void setHandleResult(String handleResult) {
        this.handleResult = handleResult;
    }

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

    public ReportStatus getStatus() {
        return status;
    }

    public void setStatus(ReportStatus status) {
        this.status = status;
    }
}
