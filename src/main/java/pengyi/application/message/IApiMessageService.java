package pengyi.application.message;

import pengyi.application.message.command.CreateMessageCommand;
import pengyi.application.message.command.ListMessageCommand;
import pengyi.application.message.representation.MessageRepresentation;
import pengyi.repository.generic.Pagination;

/**
 * Created by liubowen on 2016/3/15.
 */
public interface IApiMessageService {
    MessageRepresentation show(String messageId);
    MessageRepresentation deleteByCompany(String messageId);
    MessageRepresentation create(CreateMessageCommand command);
    Pagination<MessageRepresentation> pagination(String companyId,ListMessageCommand command);


}
