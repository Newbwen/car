package pengyi.interfaces.user.company.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pengyi.application.user.company.IApiCompanyAppService;
import pengyi.application.user.company.command.EditCompanyCommand;
import pengyi.application.user.company.command.UpdateFolderCommand;
import pengyi.application.user.company.representation.CompanyRepresentation;
import pengyi.core.api.BaseResponse;
import pengyi.core.api.ResponseCode;
import pengyi.core.exception.ConcurrencyException;

/**
 * Created by YJH on 2016/3/15.
 */
@Controller
@RequestMapping("/company/api")
public class ApiCompanyController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IApiCompanyAppService apiCompanyAppService;

    @RequestMapping(value = "/info")
    @ResponseBody
    public BaseResponse show(@PathVariable String id) {
        long startTime = System.currentTimeMillis();
        BaseResponse response = null;
        try {
            CompanyRepresentation company = apiCompanyAppService.info(id);
            response = new BaseResponse(ResponseCode.RESPONSE_CODE_SUCCESS, 0, company, ResponseCode.RESPONSE_CODE_SUCCESS.getMessage());
        } catch (Exception e) {
            logger.warn(e.getMessage());
            response = new BaseResponse(ResponseCode.RESPONSE_CODE_FAILURE, 0, null, e.getMessage());
        }
        response.setDebug_time(System.currentTimeMillis() - startTime);
        return response;
    }

    @RequestMapping(value = "/edit")
    @ResponseBody
    public BaseResponse edit(EditCompanyCommand command) {
        long startTime = System.currentTimeMillis();
        BaseResponse response = null;
        try {
            response = apiCompanyAppService.edit(command);
        } catch (ConcurrencyException e) {
            logger.warn(e.getMessage());
            response = new BaseResponse(ResponseCode.RESPONSE_CODE_CONCURRENCY_ERROR, 0, null, ResponseCode.RESPONSE_CODE_CONCURRENCY_ERROR.getMessage());
        } catch (Exception e) {
            logger.warn(e.getMessage());
            response = new BaseResponse(ResponseCode.RESPONSE_CODE_FAILURE, 0, null, e.getMessage());
        }
        response.setDebug_time(System.currentTimeMillis() - startTime);
        return response;
    }

    @RequestMapping(value = "/update_folder")
    @ResponseBody
    public BaseResponse updateFolder(UpdateFolderCommand command) {
        long startTime = System.currentTimeMillis();
        BaseResponse response = null;
        try {
            apiCompanyAppService.updateFolder(command);
            response = new BaseResponse(ResponseCode.RESPONSE_CODE_SUCCESS, 0, null, ResponseCode.RESPONSE_CODE_SUCCESS.getMessage());
        } catch (Exception e) {
            logger.warn(e.getMessage());
            response = new BaseResponse(ResponseCode.RESPONSE_CODE_FAILURE, 0, null, e.getMessage());
        }
        response.setDebug_time(System.currentTimeMillis() - startTime);
        return response;
    }

}
