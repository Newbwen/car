package pengyi.application.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pengyi.application.message.command.CreateMessageCommand;
import pengyi.application.message.command.ListMessageCommand;
import pengyi.core.mapping.IMappingService;
import pengyi.domain.model.message.Message;
import pengyi.domain.service.message.IMessageService;
import pengyi.repository.generic.Pagination;

/**
 * Created by liubowen on 2016/3/8.
 */
@Service("messageAppService")
public class MessageAppService implements IMessageAppService{
    @Autowired
    private IMessageService messageService;

    @Autowired
    private IMappingService mappingService;


    @Override
    public Pagination<Message> pagination(ListMessageCommand command) {
        command.verifyPage();
        command.verifyPageSize(20);
        Pagination<Message> pagination=messageService.pagination(command);
        return null;
    }

    @Override
    public Message create(CreateMessageCommand command) {
        return null;
    }

    @Override
    public Message show(String id) {
        return null;
    }
}
