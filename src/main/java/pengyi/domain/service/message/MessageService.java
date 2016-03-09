package pengyi.domain.service.message;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pengyi.application.message.command.ListMessageCommand;
import pengyi.core.util.CoreDateUtils;
import pengyi.domain.model.message.IMessageRepository;
import pengyi.domain.model.message.Message;
import pengyi.domain.model.user.BaseUser;
import pengyi.repository.generic.Pagination;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by liubowen on 2016/3/7.
 */
@Service("messageService")
public class MessageService implements IMessageService {
    @Autowired
    private IMessageRepository<Message,String> messageRepository;

    @Override
    @SuppressWarnings("unchecked")
    public Message getById(String messageId) {
        return messageRepository.getById(messageId);
    }

    //添加消息
    @Override
    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    public void insert(Message message) {

        messageRepository.save(message);
    }

    //根据用户查询站内消息
    @Override
    public Pagination<Message> pagination(ListMessageCommand command) {

        List<Criterion> criterionList = new ArrayList<Criterion>();

//        criterionList.add(Restrictions.eq("receiveBaseUser", user));

        List<Order> orderList = new ArrayList<Order>();

        orderList.add(Order.desc("sendDate"));

        return messageRepository.pagination(command.getPage(),command.getPageSize(),criterionList,orderList);

    }

    //标记已读
    @Override
    @SuppressWarnings("unchecked")
    public void read(String messageId) {

        Message message = getById(messageId);

        message.setReceiveDate(CoreDateUtils.formatDateTime(new Date()));

        messageRepository.update(message);
    }

}
