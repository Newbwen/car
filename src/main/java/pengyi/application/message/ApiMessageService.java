package pengyi.application.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pengyi.application.message.command.CreateMessageCommand;
import pengyi.application.message.command.ListMessageCommand;
import pengyi.application.message.representation.MessageRepresentation;
import pengyi.core.mapping.IMappingService;
import pengyi.domain.model.message.Message;
import pengyi.domain.service.message.IMessageService;
import pengyi.repository.generic.Pagination;

import java.util.List;

/**
 * Created by liubowen on 2016/3/15.
 */
@Service("apiMessageService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class ApiMessageService implements IApiMessageService {

    @Autowired
    private IMessageService messageService;

    @Autowired
    private IMappingService mappingService;

    @Override
    @Transactional(readOnly = true)
    public MessageRepresentation show(String messageId) {

        return mappingService.map(messageService.show(messageId), MessageRepresentation.class, false);
    }

    @Override
    @Transactional(readOnly = true)
    public MessageRepresentation deleteByCompany(String messageId) {

        return mappingService.map(messageService.delete(messageId), MessageRepresentation.class, false);
    }

    @Override
    @Transactional(readOnly = true)
    public MessageRepresentation create(CreateMessageCommand command) {
        return mappingService.map(messageService.create(command),MessageRepresentation.class,false);
    }

    @Override
    public Pagination<MessageRepresentation> pagination(String companyId, ListMessageCommand command) {
        command.verifyPage();

        command.verifyPageSize(20);

        Pagination<Message> pagination = messageService.pagination(command);

        List<MessageRepresentation> date = mappingService.mapAsList(pagination.getData(), MessageRepresentation.class);

        return new Pagination<MessageRepresentation>(date, pagination.getCount(), pagination.getPage(), pagination.getPageSize());

    }

}
