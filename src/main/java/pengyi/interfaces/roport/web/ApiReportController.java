package pengyi.interfaces.roport.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pengyi.application.report.IApiReportService;
import pengyi.application.report.command.ListReportCommand;
import pengyi.application.report.representation.ReportRepresentation;
import pengyi.core.api.BaseResponse;
import pengyi.core.api.ResponseCode;
import pengyi.repository.generic.Pagination;

/**
 * Created by liubowen on 2016/3/16.
 */
@Controller
@RequestMapping("/report_show/api")
public class ApiReportController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IApiReportService apiReportService;

    @RequestMapping(value = "showAll_by_company")
    public BaseResponse searchByCompany(@PathVariable String companyId, ListReportCommand command) {
        long startTime = System.currentTimeMillis();
        BaseResponse response = null;
        try {
            Pagination<ReportRepresentation> representation = apiReportService.showByCompany(companyId, command);
            response = new BaseResponse(ResponseCode.RESPONSE_CODE_CONCURRENCY_ERROR, 0, representation, ResponseCode.RESPONSE_CODE_SUCCESS.getMessage());


        } catch (Exception e) {

            logger.warn(e.getMessage());
            response=new BaseResponse(ResponseCode.RESPONSE_CODE_FAILURE,0,null,e.getMessage());
        }
        response.setDebug_time(System.currentTimeMillis()-startTime);
        return response;
    }
    @RequestMapping(value = "show_by_reportId")
    public BaseResponse showByReportId(@PathVariable String reportId){
        long startTime=System.currentTimeMillis();
        BaseResponse response=null;
        try{
            ReportRepresentation representation=apiReportService.show(reportId);
            response=new BaseResponse(ResponseCode.RESPONSE_CODE_CONCURRENCY_ERROR,0,representation,ResponseCode.RESPONSE_CODE_SUCCESS.getMessage());

        }catch (Exception e){
            logger.warn(e.getMessage());
            response=new BaseResponse(ResponseCode.RESPONSE_CODE_FAILURE,0,null,e.getMessage());

        }
        response.setDebug_time(System.currentTimeMillis()-startTime);
        return  response;
    }
}
