package pengyi.domain.service.message;

import pengyi.domain.model.message.Message;
import pengyi.domain.model.user.BaseUser;

import java.util.List;

/**
 * Created by liubowen on 2016/3/7.
 */
public interface IMessageService {

    Message getById(String messageId);

    void insert(Message message);

    List<Message> getMessageList(BaseUser user);

    void read(String messageId);


}
