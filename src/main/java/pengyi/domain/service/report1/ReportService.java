package pengyi.domain.service.report;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pengyi.core.util.CoreDateUtils;
import pengyi.domain.model.report.Report;
import pengyi.repository.generic.Pagination;
import pengyi.repository.report.ReportResposition;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by liubowen on 2016/3/7.
 */
@Service("reportService")
public class ReportService implements IReportService {

    @Autowired
    private ReportResposition reportResposition;

    @Override
    @SuppressWarnings("unchecked")//添加举报订单信息
    public void preortOrder(Report report) {

        report.setReportTime(CoreDateUtils.formatDateTime(new Date()));

        reportResposition.save(report);
    }

    @Override
    @SuppressWarnings("unchecked")//修改举报订单状态
    //状态1待处理.2处理中.3处理完成
    public void updateState(String reportId) {

        Report report = getById(reportId);

        switch (report.getStatus()) {

            case 1:
                report.setStatus(2);
                report.setStartDealTime(CoreDateUtils.formatDateTime(new Date()));
                break;

            case 2:
                report.setStatus(3);
                report.setEndDealTime(CoreDateUtils.formatDateTime(new Date()));
                break;
        }

        report.setEndDealTime(CoreDateUtils.formatDateTime(new Date()));

        reportResposition.update(report);
    }

    @Override
    @SuppressWarnings("unchecked")//根据举报订单id查询
    public Report getById(String reportId) {
        return reportResposition.getById(reportId);
    }

    @Override
    @SuppressWarnings("unchecked")//根据用户分页
    public Pagination<Report> paginationByUser(int page, int pageSize, String userId) {
        List<Criterion> criterionList = new ArrayList<Criterion>();
        criterionList.add(Restrictions.eq("reportUser.id", userId));
        List<Order> orderList = new ArrayList<Order>();
        orderList.add(Order.desc("reportTime"));
        return reportResposition.pagination(page, pageSize, (Criterion[]) criterionList.toArray(), (Order[]) orderList.toArray());
    }

    @Override
    @SuppressWarnings("unchecked")//根据举报订单id分页
    public Pagination<Report> paginationByOrder(int page, int pageSize, String orderId) {
        List<Criterion> criterionList=new ArrayList<Criterion>();
        criterionList.add(Restrictions.eq("order.id",orderId));
        List<Order> orderList=new ArrayList<Order>();
        orderList.add(Order.desc("reportTime"));
        return reportResposition.pagination(page,pageSize,(Criterion[]) criterionList.toArray(),(Order[]) orderList.toArray());
    }
}
