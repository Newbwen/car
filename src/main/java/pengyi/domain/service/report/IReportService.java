package pengyi.domain.service.report;

import pengyi.application.report.command.CreateReportCommand;
import pengyi.application.report.command.EditReportCommand;
import pengyi.application.report.command.ListReportCommand;
import pengyi.domain.model.report.Report;
import pengyi.repository.generic.Pagination;

/**
 * Created by liubowen on 2016/3/7.
 */
public interface IReportService {
    Report createReport(CreateReportCommand command);

    Report updateState(EditReportCommand command);

    Report getById(String reportId);

    Pagination<Report> pagination(ListReportCommand command);
}
