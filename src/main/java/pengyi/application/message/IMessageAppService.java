package pengyi.application.message;

import pengyi.application.message.command.CreateMessageCommand;
import pengyi.application.message.command.EditMessageCommand;
import pengyi.application.message.command.ListMessageCommand;
import pengyi.application.message.representation.MessageRepresentation;
import pengyi.domain.model.message.Message;
import pengyi.repository.generic.Pagination;

/**
 * Created by liubowen on 2016/3/8.
 */
public interface IMessageAppService {

    Pagination<MessageRepresentation> pagination(ListMessageCommand command);

    MessageRepresentation create(CreateMessageCommand command);

    MessageRepresentation show(String id);

    MessageRepresentation edit(EditMessageCommand command);


}
