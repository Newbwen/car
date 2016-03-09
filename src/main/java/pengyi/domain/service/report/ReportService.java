package pengyi.domain.service.report;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pengyi.application.report.command.ListReportCommand;
import pengyi.core.util.CoreDateUtils;
import pengyi.domain.model.report.Report;
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

    @Override
    @SuppressWarnings("unchecked")//添加举报订单信息
    public void preortOrder(Report report) {

        report.setReportTime(CoreDateUtils.formatDateTime(new Date()));

        reportRepository.save(report);
    }

    @Override
    @SuppressWarnings("unchecked")//修改举报订单状态
    //状态1待处理.2处理中.3处理完成
    public void updateState(String reportId) {

//        Report report = getById(reportId);
//
//        switch (report.getStatus()) {
//
//            case 1:
//                report.setStatus(2);
//                report.setStartDealTime(CoreDateUtils.formatDateTime(new Date()));
//                break;
//
//            case 2:
//                report.setStatus(3);
//                report.setEndDealTime(CoreDateUtils.formatDateTime(new Date()));
//                break;
//        }
//
//        report.setEndDealTime(CoreDateUtils.formatDateTime(new Date()));
//
//        reportRepository.update(report);
    }

    @Override
    public Report getById(String reportId) {
        return reportRepository.getById(reportId);
    }

    @Override
    public Pagination<Report> pagination(ListReportCommand command) {
        List<Criterion> criterionList = new ArrayList<Criterion>();
//        criterionList.add(Restrictions.eq("reportUser.id", userId));
        List<Order> orderList = new ArrayList<Order>();
        orderList.add(Order.desc("reportTime"));
        return reportRepository.pagination(command.getPage(),command.getPageSize(),criterionList,orderList);
    }

}
