package pengyi.domain.service.message;

import pengyi.application.message.command.*;
import pengyi.domain.model.message.Message;
import pengyi.repository.generic.Pagination;

/**
 * Created by liubowen on 2016/3/7.
 */
public interface IMessageService {

    Message show(String messageId);

    void create(CreateMessageByRoleCommand command);

    Message createByBaseUser(CreateMessageByBaseUserCommand command);

    Pagination<Message> pagination(ListMessageCommand command);

    Message delete(String messageId);

    void companyCreate(CompanyCreateMessageCommand command);

    Pagination<Message> pagination(CompanyListMessageCommand command);


}
