package pengyi.application.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pengyi.application.message.command.CompanyListMessageCommand;
import pengyi.application.message.command.CreateMessageByBaseUserCommand;
import pengyi.application.message.command.CreateMessageByRoleCommand;
import pengyi.application.message.command.ListMessageCommand;
import pengyi.application.message.representation.MessageRepresentation;
import pengyi.core.api.BaseResponse;
import pengyi.core.api.ResponseCode;
import pengyi.core.mapping.IMappingService;
import pengyi.core.util.CoreStringUtils;
import pengyi.domain.model.message.Message;
import pengyi.domain.service.message.IMessageService;
import pengyi.repository.generic.Pagination;

import java.util.List;

/**
 * Created by liubowen on 2016/3/15.
 */
@Service("apiMessageService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class ApiMessageService implements IApiMessageService {

    @Autowired
    private IMessageService messageService;

    @Autowired
    private IMappingService mappingService;

    @Override//1
    @Transactional(readOnly = true)
    public MessageRepresentation show(String messageId) {
        Message message = messageService.show(messageId);
        if (null != message) {
            return mappingService.map(message, MessageRepresentation.class, false);
        }
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public MessageRepresentation deleteByCompany(String messageId) {

        return mappingService.map(messageService.delete(messageId), MessageRepresentation.class, false);
    }


    @Override//3
    @Transactional(readOnly = true)
    public BaseResponse apiCreateMessage(CreateMessageByBaseUserCommand command) {
        if (null != command) {
            MessageRepresentation representation = mappingService.map(messageService.createByBaseUser(command), MessageRepresentation.class, false);
            return new BaseResponse(ResponseCode.RESPONSE_CODE_SUCCESS, 0, representation, ResponseCode.RESPONSE_CODE_SUCCESS.getMessage());
        }
        return new BaseResponse(ResponseCode.RESPONSE_CODE_CONCURRENCY_ERROR, 0, null, ResponseCode.RESPONSE_CODE_CONCURRENCY_ERROR.getMessage());
    }

    @Override//4
    @Transactional(readOnly = true)
    public BaseResponse apiCreateMessage(CreateMessageByRoleCommand command) {
        if (null != command) {
            messageService.create(command);
            return new BaseResponse(ResponseCode.RESPONSE_CODE_SUCCESS, 0, null, ResponseCode.RESPONSE_CODE_SUCCESS.getMessage());
        }
        return new BaseResponse(ResponseCode.RESPONSE_CODE_CONCURRENCY_ERROR, 0, null, ResponseCode.RESPONSE_CODE_CONCURRENCY_ERROR.getMessage());
    }

    @Override//2
    @Transactional(readOnly = true)
    public BaseResponse companyMessageList(CompanyListMessageCommand command) {
        if (null != command) {
            Pagination<Message> pagination = messageService.pagination(command);
            List<MessageRepresentation> data = mappingService.mapAsList(pagination.getData(), MessageRepresentation.class);
            Pagination<MessageRepresentation> result = new Pagination<MessageRepresentation>(data, pagination.getCount(), pagination.getPage(), pagination.getPageSize());
            return new BaseResponse(ResponseCode.RESPONSE_CODE_SUCCESS, 0, result, ResponseCode.RESPONSE_CODE_SUCCESS.getMessage());
        }
        return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseCode.RESPONSE_CODE_PARAMETER_ERROR.getMessage());

    }

}
