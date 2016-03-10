package pengyi.domain.service.message;

import pengyi.application.message.command.CreateMessageCommand;
import pengyi.application.message.command.EditMessageCommand;
import pengyi.application.message.command.ListMessageCommand;
import pengyi.domain.model.message.Message;
import pengyi.domain.model.user.BaseUser;
import pengyi.repository.generic.Pagination;

import java.util.List;

/**
 * Created by liubowen on 2016/3/7.
 */
public interface IMessageService {

    Message show(String messageId);

    Message create(CreateMessageCommand command);

    Pagination<Message> pagination(ListMessageCommand command);

    Message edit(EditMessageCommand command);


}
