package pengyi.application.report;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pengyi.application.report.command.EditReportCommand;
import pengyi.application.report.command.ListReportCommand;
import pengyi.application.report.representation.ReportRepresentation;
import pengyi.core.mapping.IMappingService;
import pengyi.domain.model.report.Report;
import pengyi.domain.service.report.IReportService;
import pengyi.repository.generic.Pagination;

import java.util.List;

/**
 * Created by liubowen on 2016/3/16.
 */
@Service("apiReportService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class ApiReportService implements IApiReportService {

    @Autowired
    private IReportService reportService;

    @Autowired
    private IMappingService mappingService;

    @Override
    public ReportRepresentation show(String reportId) {
        return mappingService.map(reportService.getById(reportId), ReportRepresentation.class, false);
    }

    @Override
    public Pagination<ReportRepresentation> showByCompany(String companyId, ListReportCommand command) {
        command.verifyPage();
        command.verifyPageSize(20);
        Pagination<Report> pagination = reportService.pagination(command);
        List<ReportRepresentation> date = mappingService.mapAsList(pagination.getData(), ReportRepresentation.class);

        return new Pagination<ReportRepresentation>(date, pagination.getCount(), pagination.getPage(), pagination.getPageSize());

    }

    @Override
    public ReportRepresentation edit(EditReportCommand command) {
        return null;
    }


}

