package pengyi.domain.service.report;

import pengyi.application.report.command.ListReportCommand;
import pengyi.domain.model.report.Report;
import pengyi.repository.generic.Pagination;

/**
 * Created by liubowen on 2016/3/7.
 */
public interface IReportService {
    void preortOrder(Report report);

    void updateState(String reportId);

    Report getById(String reportId);

    Pagination<Report> pagination(ListReportCommand command);
}
