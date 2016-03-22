package pengyi.application.report;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pengyi.application.report.command.CreateReportCommand;
import pengyi.application.report.command.EditReportCommand;
import pengyi.application.report.command.ListReportCommand;
import pengyi.application.report.representation.ReportRepresentation;
import pengyi.core.api.BaseResponse;
import pengyi.core.api.ResponseCode;
import pengyi.core.mapping.IMappingService;
import pengyi.domain.model.message.Message;
import pengyi.domain.model.report.Report;
import pengyi.domain.service.report.IReportService;
import pengyi.repository.generic.Pagination;

import java.util.List;

/**
 * Created by liubowen on 2016/3/16.
 */
@Service("apiReportService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class ApiReportAppAppService implements IApiReportAppService {

    @Autowired
    private IReportService reportService;

    @Autowired
    private IMappingService mappingService;


    @Override
    @Transactional(readOnly = true)
    public ReportRepresentation show(String reportId) {
        Report report = reportService.getById(reportId);
        if (null != report) {
            return mappingService.map(report, ReportRepresentation.class, false);
        }
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public BaseResponse list(ListReportCommand command) {
        if (null != command) {

            Pagination<Report> pagination=reportService.pagination(command);
            List<ReportRepresentation> data=mappingService.mapAsList(pagination.getData(),ReportRepresentation.class);
            Pagination<ReportRepresentation> result=new Pagination<ReportRepresentation>(data,pagination.getCount(),pagination.getPage(),pagination.getPageSize());
            return new BaseResponse(ResponseCode.RESPONSE_CODE_SUCCESS,0,result,ResponseCode.RESPONSE_CODE_SUCCESS.getMessage());

        }
        return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR,0,null,ResponseCode.RESPONSE_CODE_PARAMETER_ERROR.getMessage());
    }

    @Override
    public BaseResponse create(CreateReportCommand command) {
        if(null !=command){
            ReportRepresentation representation=mappingService.map(reportService.createReport(command),ReportRepresentation.class,false);
            return  new BaseResponse(ResponseCode.RESPONSE_CODE_SUCCESS,0,representation,ResponseCode.RESPONSE_CODE_SUCCESS.getMessage());
        }
        return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR,0,null,ResponseCode.RESPONSE_CODE_PARAMETER_ERROR.getMessage());
    }
}

