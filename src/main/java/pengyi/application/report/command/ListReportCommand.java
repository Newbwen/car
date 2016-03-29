package pengyi.application.report.command;

import pengyi.core.commons.command.BasicPaginationCommand;
import pengyi.core.type.ReportStatus;

import javax.xml.crypto.Data;
import java.util.Date;

/**
 * Created by liubowen on 2016/3/9.
 */
public class ListReportCommand extends BasicPaginationCommand {
    private String reportUser;      //举报人
    private String order;          //举报订单
    private ReportStatus status;   //状态（待处理、处理中、处理完成）
    private Date startDealTime;
    private Date endDealTime;


    public Date getStartDealTime() {
        return startDealTime;
    }

    public void setStartDealTime(Date startDealTime) {
        this.startDealTime = startDealTime;
    }

    public Date getEndDealTime() {
        return endDealTime;
    }

    public void setEndDealTime(Date endDealTime) {
        this.endDealTime = endDealTime;
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
