package pengyi.domain.service.message;

import pengyi.application.message.command.ListMessageCommand;
import pengyi.domain.model.message.Message;
import pengyi.domain.model.user.BaseUser;
import pengyi.repository.generic.Pagination;

import java.util.List;

/**
 * Created by liubowen on 2016/3/7.
 */
public interface IMessageService {

    Message getById(String messageId);

    void insert(Message message);

    Pagination<Message> pagination(ListMessageCommand command);

    void read(String messageId);


}
