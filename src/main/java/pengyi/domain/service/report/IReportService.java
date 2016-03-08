package pengyi.domain.service.report;

import pengyi.domain.model.report.Report;
import pengyi.repository.generic.Pagination;

/**
 * Created by liubowen on 2016/3/7.
 */
public interface IReportService {
    void preortOrder(Report report);

    void updateState(String reportId);

    Report getById(String reportId);

    Pagination<Report> paginationByUser(int page, int pageSize, String userId);

    Pagination<Report> paginationByOrder(int page, int pageSize, String orderId);

}
