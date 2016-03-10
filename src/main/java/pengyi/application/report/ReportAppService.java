package pengyi.application.report;


import org.springframework.beans.factory.annotation.Autowired;
import pengyi.application.report.command.CreateReportCommand;
import pengyi.application.report.command.EditReportCommand;
import pengyi.application.report.command.ListReportCommand;
import pengyi.application.report.representation.ReportRepresentation;
import pengyi.core.mapping.IMappingService;
import pengyi.domain.model.report.Report;
import pengyi.domain.service.report.IReportService;
import pengyi.repository.generic.Pagination;

import java.util.List;

/**
 * Created by liubowen on 2016/3/10.
 */
public class ReportAppService implements IReportAppService {

    @Autowired
    private IReportService reportService;

    @Autowired
    private IMappingService mappingService;

    @Override
    public Pagination<ReportRepresentation> pagination(ListReportCommand command) {

        command.verifyPage();

        command.verifyPageSize(20);

        Pagination<Report> pagination = reportService.pagination(command);

        List<ReportRepresentation> data = mappingService.mapAsList(pagination.getData(), ReportRepresentation.class);

        return new Pagination<ReportRepresentation>(data, pagination.getCount(), pagination.getPage(), pagination.getPageSize());
    }

    @Override
    public ReportRepresentation create(CreateReportCommand command) {
        return mappingService.map(reportService.createReport(command),ReportRepresentation.class,false);
    }

    @Override
    public ReportRepresentation edit(EditReportCommand command) {
        return mappingService.map(reportService.updateState(command),ReportRepresentation.class,false);
    }

    @Override
    public ReportRepresentation show(String reportId) {
        return mappingService.map(reportService.getById(reportId),ReportRepresentation.class,false);
    }

    @Override
    public ReportRepresentation updateStatus(EditReportCommand command) {
        return mappingService.map(reportService.updateState(command),ReportRepresentation.class,false);
    }
}