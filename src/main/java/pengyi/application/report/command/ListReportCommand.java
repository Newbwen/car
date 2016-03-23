package pengyi.application.report.command;

import pengyi.core.commons.command.BasicPaginationCommand;
import pengyi.core.type.ReportStatus;

import javax.xml.crypto.Data;

/**
 * Created by liubowen on 2016/3/9.
 */
public class ListReportCommand extends BasicPaginationCommand {
    private String reportUser;      //举报人
    private String order;          //举报订单
    private ReportStatus status;   //状态（待处理、处理中、处理完成）
    private Data beginTime;
    private Data endTime;

    public Data getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Data beginTime) {
        this.beginTime = beginTime;
    }

    public Data getEndTime() {
        return endTime;
    }

    public void setEndTime(Data endTime) {
        this.endTime = endTime;
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

    public ReportStatus getStatus() {
        return status;
    }

    public void setStatus(ReportStatus status) {
        this.status = status;
    }
}
