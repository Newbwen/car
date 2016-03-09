package pengyi.application.message;

import pengyi.application.message.command.CreateMessageCommand;
import pengyi.application.message.command.ListMessageCommand;
import pengyi.domain.model.message.Message;
import pengyi.repository.generic.Pagination;

/**
 * Created by liubowen on 2016/3/8.
 */
public interface IMessageAppService {
    Pagination<Message> pagination(ListMessageCommand command);

    Message create(CreateMessageCommand command);

    Message show(String id);


}
