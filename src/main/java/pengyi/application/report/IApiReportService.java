package pengyi.application.report;

import pengyi.application.report.command.EditReportCommand;
import pengyi.application.report.command.ListReportCommand;
import pengyi.application.report.representation.ReportRepresentation;
import pengyi.repository.generic.Pagination;

/**
 * Created by liubowen on 2016/3/16.
 */
public interface IApiReportService {
    ReportRepresentation show(String reportId);
    Pagination<ReportRepresentation> showByCompany(String companyId,ListReportCommand command);
    ReportRepresentation edit(EditReportCommand command);

}
