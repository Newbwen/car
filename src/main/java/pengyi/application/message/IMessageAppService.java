package pengyi.application.message;

import pengyi.application.message.command.CompanyCreateMessageCommand;
import pengyi.application.message.command.CreateMessageByBaseUserCommand;
import pengyi.application.message.command.CreateMessageByRoleCommand;
import pengyi.application.message.command.ListMessageCommand;
import pengyi.application.message.representation.MessageRepresentation;
import pengyi.repository.generic.Pagination;

/**
 * Created by liubowen on 2016/3/8.
 */
public interface IMessageAppService {

    Pagination<MessageRepresentation> pagination(ListMessageCommand command);

    void create(CreateMessageByRoleCommand command);

    MessageRepresentation show(String id);

    MessageRepresentation delete(String messageId);

    MessageRepresentation createByBaseUser(CreateMessageByBaseUserCommand command);

    void companyCreateMessageByRole(CompanyCreateMessageCommand command);

}
