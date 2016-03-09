package pengyi.application.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pengyi.application.message.command.CreateMessageCommand;
import pengyi.application.message.command.EditMessageCommand;
import pengyi.application.message.command.ListMessageCommand;
import pengyi.application.message.representation.MessageRepresentation;
import pengyi.core.mapping.IMappingService;
import pengyi.domain.model.message.Message;
import pengyi.domain.service.message.IMessageService;
import pengyi.repository.generic.Pagination;

import java.util.List;

/**
 * Created by liubowen on 2016/3/8.
 */
@Service("messageAppService")
public class MessageAppService implements IMessageAppService {
    @Autowired
    private IMessageService messageService;

    @Autowired
    private IMappingService mappingService;


    @Override
    public Pagination<MessageRepresentation> pagination(ListMessageCommand command) {

        command.verifyPage();

        command.verifyPageSize(20);

        Pagination<Message> pagination = messageService.pagination(command);

        List<MessageRepresentation> date = mappingService.mapAsList(pagination.getData(), MessageRepresentation.class);

        return new Pagination<MessageRepresentation>(date, pagination.getCount(), pagination.getPage(), pagination.getPageSize());
    }

    @Override
    public MessageRepresentation create(CreateMessageCommand command) {

        return mappingService.map(messageService.create(command), MessageRepresentation.class, false);
    }

    @Override
    public MessageRepresentation show(String id) {

        return mappingService.map(messageService.show(id), MessageRepresentation.class, false);
    }

    @Override
    public MessageRepresentation edit(EditMessageCommand command) {

        return mappingService.map(messageService.edit(command), MessageRepresentation.class, false);
    }
}
