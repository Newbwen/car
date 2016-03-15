package pengyi.domain.service.message;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pengyi.application.message.command.CreateMessageCommand;
import pengyi.application.message.command.DeleteMessageCommand;
import pengyi.application.message.command.EditMessageCommand;
import pengyi.application.message.command.ListMessageCommand;
import pengyi.application.message.representation.MessageRepresentation;
import pengyi.core.exception.NoFoundException;
import pengyi.core.util.CoreDateUtils;
import pengyi.domain.model.message.IMessageRepository;
import pengyi.domain.model.message.Message;
import pengyi.domain.model.user.BaseUser;
import pengyi.domain.service.user.user.IUserService;
import pengyi.repository.generic.Pagination;
import pengyi.repository.message.MessageRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by liubowen on 2016/3/7.
 */
@Service("messageService")
public class MessageService implements IMessageService {
    @Autowired
    private IMessageRepository<Message, String> messageRepository;

    @Autowired
    private IUserService userService;

    @Override
    public Message show(String messageId) {
        Message message = messageRepository.getById(messageId);
        if (null == message) {
            throw new NoFoundException("没有找到messageId=[" + messageId + "]的记录");
        }
        return message;
    }

    @Override/*添加的时候将发送时间设置为当前时间，接收时间为Null*/
    public Message create(CreateMessageCommand command) {
        //从service中获取用户
        BaseUser sendUser = userService.show(command.getSendBaseUser());

        BaseUser receiveUser = userService.show(command.getReceiveBaseUser());

        Message message = new Message(sendUser, receiveUser, new Date(), null, command.getContent(), command.getType());
        messageRepository.save(message);

        return message;
    }

    @Override
    public Pagination<Message> pagination(ListMessageCommand command) {

        List<Criterion> criterionList = new ArrayList<Criterion>();

//        criterionList.add(Restrictions.eq("receiveBaseUser", user));

        List<Order> orderList = new ArrayList<Order>();

        orderList.add(Order.desc("sendDate"));

        return messageRepository.pagination(command.getPage(), command.getPageSize(), criterionList, orderList);

    }

    @Override
    public Message delete(String messageId) {
        Message message=messageRepository.getById(messageId);
        messageRepository.delete(message);
        return message;
    }


}
