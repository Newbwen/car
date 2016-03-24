package pengyi.interfaces.message.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pengyi.application.message.IApiMessageAppService;
import pengyi.application.message.command.CompanyCreateMessageCommand;
import pengyi.application.message.command.CompanyListMessageCommand;
import pengyi.application.message.representation.MessageRepresentation;
import pengyi.core.api.BaseResponse;
import pengyi.core.api.ResponseCode;
import pengyi.repository.generic.Pagination;


/**
 * Created by liubowen on 2016/3/15.
 */
@Controller
@RequestMapping("/message/api")
public class ApiMessageController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IApiMessageAppService apiMessageService;

    @RequestMapping(value = "/search_by_company")
    @ResponseBody
    public BaseResponse listMessageByCompany(CompanyListMessageCommand command) {
        long startTime = System.currentTimeMillis();
        BaseResponse response = null;
        try {
             Pagination<MessageRepresentation> pagination= apiMessageService.companyMessageList(command);
            response = new BaseResponse(ResponseCode.RESPONSE_CODE_SUCCESS, 0, pagination, ResponseCode.RESPONSE_CODE_SUCCESS.getMessage());
        } catch (Exception e) {
            logger.warn(e.getMessage());
            response = new BaseResponse(ResponseCode.RESPONSE_CODE_FAILURE, 0, null, e.getMessage());
        }
        response.setDebug_time(System.currentTimeMillis() - startTime);
        return response;
    }

    @RequestMapping(value = "/delete")
    @ResponseBody
    public BaseResponse deleteByCompany(String messageId) {
        long startTime = System.currentTimeMillis();
        BaseResponse response = null;
        try {
            MessageRepresentation representation = apiMessageService.deleteByCompany(messageId);
            response = new BaseResponse(ResponseCode.RESPONSE_CODE_FAILURE, 0, representation, ResponseCode.RESPONSE_CODE_SUCCESS.getMessage());

        } catch (Exception e) {
            logger.warn(e.getMessage());
            response = new BaseResponse(ResponseCode.RESPONSE_CODE_FAILURE, 0, null, e.getMessage());

        }
        response.setDebug_time((System.currentTimeMillis() - startTime));
        return response;
    }

    @RequestMapping(value = "/create_by_base_user")
    @ResponseBody
    public BaseResponse createMessage(CompanyCreateMessageCommand command) {
        long startTime = System.currentTimeMillis();
        BaseResponse response = null;
        try {
            response = apiMessageService.apiCreateMessage(command);
        } catch (Exception e) {
            logger.warn(e.getMessage());
            response = new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, e.getMessage());
        }
        response.setDebug_time(System.currentTimeMillis() - startTime);
        return response;
    }

    @RequestMapping(value = "/show_by_messageId")
    @ResponseBody
    public BaseResponse showByMessageId(String messageId) {

        long startTime = System.currentTimeMillis();
        BaseResponse response = null;
        try {
            MessageRepresentation message = apiMessageService.show(messageId);
            response = new BaseResponse(ResponseCode.RESPONSE_CODE_SUCCESS, 0, message, ResponseCode.RESPONSE_CODE_SUCCESS.getMessage());
        } catch (Exception e) {
            logger.warn(e.getMessage());
            response = new BaseResponse(ResponseCode.RESPONSE_CODE_FAILURE, 0, null, e.getMessage());
        }
        response.setDebug_time(System.currentTimeMillis() - startTime);
        return response;
    }

}
