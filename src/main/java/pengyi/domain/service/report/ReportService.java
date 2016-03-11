package pengyi.domain.service.report;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pengyi.application.report.command.CreateReportCommand;
import pengyi.application.report.command.EditReportCommand;
import pengyi.application.report.command.ListReportCommand;
import pengyi.core.exception.NoFoundException;
import pengyi.core.type.ReportStatus;
import pengyi.domain.model.order.Order;
import pengyi.domain.model.report.Report;
import pengyi.domain.model.user.BaseUser;
import pengyi.domain.service.order.IOrderService;
import pengyi.domain.service.user.IBaseUserService;
import pengyi.repository.generic.Pagination;
import pengyi.repository.report.ReportRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by liubowen on 2016/3/7.
 */
@Service("reportService")
public class ReportService implements IReportService {

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private IBaseUserService baseUserService;

    @Autowired
    private IOrderService orderService;

    @Override
    public Report createReport(CreateReportCommand command) {

        BaseUser baseUser = baseUserService.show(command.getReportUser());

        Order order = orderService.show(command.getOrder());

        Report report = new Report(baseUser, order, new Date(), null, null, command.getDescription(), ReportStatus.PENDING);

        return report;
    }

    @Override
    @SuppressWarnings("unchecked")//修改举报订单状态
    //状态1待处理.2处理中.3处理完成
    public Report updateState(EditReportCommand command) {

        Report report = this.getById(command.getId());

        report.fainWhenConcurrencyViolation(command.getVersion());
        /* PENDING("待处理",1,Boolean.FALSE),
        IN_PROCESS("正在处理",2,Boolean.FALSE),
        FIGURE_OUT("处理完成",3,Boolean.FALSE);*/
        if (report.getStatus().equals("PENDING")) {
            report.setStatus(ReportStatus.IN_PROCESS);
        } else if (report.getStatus().equals("IN_PROCESS")) {
            report.setStatus(ReportStatus.FIGURE_OUT);
        }
        reportRepository.update(report);
        return report;
    }

    @Override
    public Report getById(String reportId) {
        Report report = reportRepository.getById(reportId);
        if (report == null) {
            throw new NoFoundException("没有找到id=[" + reportId + "]的记录");
        }
        return report;
    }

    @Override
    public Pagination<Report> pagination(ListReportCommand command) {
        List<Criterion> criterionList = new ArrayList<Criterion>();
        criterionList.add(Restrictions.eq("reportUser.id", command.getReportUser()));
        List<org.hibernate.criterion.Order> orderList = new ArrayList<org.hibernate.criterion.Order>();
        orderList.add(org.hibernate.criterion.Order.desc("reportTime"));
        return reportRepository.pagination(command.getPage(), command.getPageSize(), criterionList, orderList);
    }

}
