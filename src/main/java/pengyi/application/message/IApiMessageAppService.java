package pengyi.application.message;

import pengyi.application.message.command.CompanyListMessageCommand;
import pengyi.application.message.command.CreateMessageByBaseUserCommand;
import pengyi.application.message.command.CreateMessageByRoleCommand;
import pengyi.application.message.command.ListMessageCommand;
import pengyi.application.message.representation.MessageRepresentation;
import pengyi.core.api.BaseResponse;
import pengyi.repository.generic.Pagination;

/**
 * Created by liubowen on 2016/3/15.
 */
public interface IApiMessageAppService {
    MessageRepresentation show(String messageId);

    MessageRepresentation deleteByCompany(String messageId);

    BaseResponse apiCreateMessage(CreateMessageByBaseUserCommand command);

    BaseResponse apiCreateMessage(CreateMessageByRoleCommand command);

    BaseResponse companyMessageList(CompanyListMessageCommand command);

}
