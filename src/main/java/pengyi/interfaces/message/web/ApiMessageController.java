package pengyi.interfaces.message.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pengyi.application.message.IApiMessageService;
import pengyi.application.message.command.CreateMessageCommand;
import pengyi.application.message.command.ListMessageCommand;
import pengyi.application.message.representation.MessageRepresentation;
import pengyi.core.api.BaseResponse;
import pengyi.core.api.ResponseCode;
import pengyi.repository.generic.Pagination;

/**
 * Created by liubowen on 2016/3/15.
 */
@Controller
@RequestMapping("/message_show/api")
public class ApiMessageController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IApiMessageService apiMessageService;

    @RequestMapping(value = "search_by_company")
    public BaseResponse searchByCompany(@PathVariable String companyId) {
        long startTime = System.currentTimeMillis();
        BaseResponse response = null;
        try {
            MessageRepresentation representation = apiMessageService.show(companyId);
            response = new BaseResponse(ResponseCode.RESPONSE_CODE_CONCURRENCY_ERROR, 0, representation, ResponseCode.RESPONSE_CODE_SUCCESS.getMessage());
        } catch (Exception e) {
            logger.warn(e.getMessage());
            response = new BaseResponse(ResponseCode.RESPONSE_CODE_FAILURE, 0, null, e.getMessage());
        }
        response.setDebug_time(System.currentTimeMillis() - startTime);
        return response;
    }

    @RequestMapping(value="deleteMessage_by_company")
    public BaseResponse deleteByCompany(@PathVariable String messageId) {
        long startTime = System.currentTimeMillis();
        BaseResponse response = null;
        try{
            MessageRepresentation representation=apiMessageService.deleteByCompany(messageId);
            response=new BaseResponse(ResponseCode.RESPONSE_CODE_FAILURE,0,representation,ResponseCode.RESPONSE_CODE_SUCCESS.getMessage());

        }catch (Exception e){
            logger.warn(e.getMessage());
            response=new BaseResponse(ResponseCode.RESPONSE_CODE_FAILURE,0,null,e.getMessage());

        }
        response.setDebug_time((System.currentTimeMillis()-startTime));
        return response;
    }
    @RequestMapping(value="createMessage")
    public BaseResponse createMessage(@PathVariable CreateMessageCommand command){
        long startTime=System.currentTimeMillis();
        BaseResponse response=null;
        try{
            MessageRepresentation representation=apiMessageService.create(command);
            response=new BaseResponse(ResponseCode.RESPONSE_CODE_FAILURE,0,representation,ResponseCode.RESPONSE_CODE_SUCCESS.getMessage());
        }catch (Exception e){
            logger.warn(e.getMessage());
            response=new BaseResponse(ResponseCode.RESPONSE_CODE_FAILURE,0,null,e.getMessage());
        }
        response.setDebug_time(System.currentTimeMillis()-startTime);
        return response;

    }
    @RequestMapping(value = "showByCompanyId")
    public BaseResponse showAllByCompany(@PathVariable String companyId, ListMessageCommand command){

        long startTime=System.currentTimeMillis();
        BaseResponse response=null;
        try{
            Pagination<MessageRepresentation> representation=apiMessageService.pagination(companyId,command);
            response=new BaseResponse(ResponseCode.RESPONSE_CODE_FAILURE,0,representation,ResponseCode.RESPONSE_CODE_SUCCESS.getMessage());
        }catch (Exception e){
            logger.warn(e.getMessage());
            response=new BaseResponse(ResponseCode.RESPONSE_CODE_FAILURE,0,null,e.getMessage());
        }
        response.setDebug_time(System.currentTimeMillis()-startTime);
        return response;

    }

}
