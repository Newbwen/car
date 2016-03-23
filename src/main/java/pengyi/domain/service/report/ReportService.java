package pengyi.domain.service.report;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pengyi.application.report.command.CreateReportCommand;
import pengyi.application.report.command.EditReportCommand;
import pengyi.application.report.command.ListReportCommand;
import pengyi.core.exception.NoFoundException;
import pengyi.core.type.ReportStatus;
import pengyi.core.util.CoreStringUtils;
import pengyi.domain.model.order.Order;
import pengyi.domain.model.report.Report;
import pengyi.domain.model.user.BaseUser;
import pengyi.domain.service.order.IOrderService;
import pengyi.domain.service.user.IBaseUserService;
import pengyi.repository.generic.Pagination;
import pengyi.repository.report.ReportRepository;

import java.util.*;

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

        Report report = new Report(baseUser, order, new Date(), null, null, command.getDescription(), ReportStatus.PENDING, null);

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
        Map<String, String> aliasMap = new HashMap<String, String>();
        if (!CoreStringUtils.isEmpty(command.getReportUser())) {
            criterionList.add(Restrictions.like("reportUser", command.getReportUser(), MatchMode.ANYWHERE));
        }
        if (!CoreStringUtils.isEmpty(command.getOrder())) {
            aliasMap.put("order", "order");
            criterionList.add(Restrictions.like("order.orderNumber", command.getOrder(), MatchMode.ANYWHERE));
        }
        if (null !=command.getBeginTime() && null !=command.getEndTime()) {
            criterionList.add(Restrictions.between("reportTime",command.getBeginTime(),command.getEndTime()));
        }
        List<org.hibernate.criterion.Order> orderList = new ArrayList<org.hibernate.criterion.Order>();
        orderList.add(org.hibernate.criterion.Order.desc("reportTime"));
        return reportRepository.pagination(command.getPage(), command.getPageSize(), criterionList,aliasMap, orderList,null,null);
    }

}
