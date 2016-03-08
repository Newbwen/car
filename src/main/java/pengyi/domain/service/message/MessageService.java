package pengyi.domain.service.message;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pengyi.core.util.CoreDateUtils;
import pengyi.domain.model.message.IMessageRepository;
import pengyi.domain.model.message.Message;
import pengyi.domain.model.user.BaseUser;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by liubowen on 2016/3/7.
 */
@Service("messageService")
public class MessageService implements IMessageService {
    @Autowired
    private IMessageRepository messageResposition;

    @Override
    @SuppressWarnings("unchecked")
    public Message getById(String messageId) {
        return (Message) messageResposition.getById(messageId);
    }

    //添加消息
    @Override
    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    public void insert(Message message) {

        messageResposition.save(message);
    }

    //根据用户查询站内消息
    @Override
    @SuppressWarnings("unchecked")
    public List<Message> getMessageList(BaseUser user) {

        List<Criterion> criterionList = new ArrayList<Criterion>();

        criterionList.add(Restrictions.eq("receiveBaseUser", user));

        List<Order> orderList = new ArrayList<Order>();

        orderList.add(Order.desc("sendDate"));

        return messageResposition.list((Criterion[])criterionList.toArray(), (Order[]) orderList.toArray());
    }

    //标记已读
    @Override
    @SuppressWarnings("unchecked")
    public void read(String messageId) {

        Message message = getById(messageId);

        message.setReceiveDate(CoreDateUtils.formatDateTime(new Date()));

        messageResposition.update(message);
    }

}
