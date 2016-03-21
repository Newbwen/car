package pengyi.domain.service.message;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pengyi.application.message.command.*;
import pengyi.core.exception.NoFoundException;
import pengyi.core.type.ShowType;
import pengyi.core.util.CoreStringUtils;
import pengyi.domain.model.message.IMessageRepository;
import pengyi.domain.model.message.Message;
import pengyi.domain.model.role.Role;
import pengyi.domain.model.user.BaseUser;
import pengyi.domain.model.user.company.Company;
import pengyi.domain.service.role.IRoleService;
import pengyi.domain.service.user.IBaseUserService;
import pengyi.domain.service.user.company.ICompanyService;
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
    private IMessageRepository<Message, String> messageRepository;

    @Autowired
    private IBaseUserService baseUserService;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private ICompanyService companyService;

    @Override
    public Message show(String messageId) {
        Message message = messageRepository.getById(messageId);
        if (null == message) {
            throw new NoFoundException("没有找到messageId=[" + messageId + "]的记录");
        }
        return message;
    }

    @Override
    //发送给角色
    /*添加的时候将发送时间设置为当前时间，接收时间为Null*/
    public Message create(CreateMessageByRoleCommand command) {
        //从service中获取用户
        BaseUser sendUser = baseUserService.show(command.getSendBaseUser());

        //获取角色
        Role role = roleService.show(command.getUserRole());

        List<BaseUser> baseUsers = baseUserService.searchByUserRole(role.getId());

        for (BaseUser item : baseUsers) {
            Message message = new Message(sendUser, item, new Date(), null, command.getContent(), command.getType(), ShowType.SHOW);
            messageRepository.save(message);
            return message;
        }

        return null;
//        Message message = new Message(sendUser, receiveUser, new Date(), null, command.getContent(), command.getType(),ShowType.SHOW);


//        return message;
    }
//发送给用户
    @Override
    public Message createByBaseUser(CreateMessageByBaseUserCommand command) {
        BaseUser sendUser = baseUserService.show(command.getSendBaseUser());

        BaseUser receiveBaseUser = baseUserService.show(command.getReceiveBaseUser());
        Message message = new Message(sendUser, receiveBaseUser, new Date(), null, command.getContent(), command.getType(), ShowType.SHOW);
        return message;
    }

    @Override
    public Pagination<Message> pagination(ListMessageCommand command) {

        List<Criterion> criterionList = new ArrayList();

        if(!CoreStringUtils.isEmpty(command.getCompany())) {
            List<Company> companies = companyService.apiByName(command.getCompany());
            if(null !=companies){
                criterionList.add(Restrictions.in("sendBaseUser", companies));
            }
        }
        if(!CoreStringUtils.isEmpty(command.getContent())){
            criterionList.add(Restrictions.like("content",command.getContent(), MatchMode.ANYWHERE));
        }
        if (null != command.getShowType()) {
            criterionList.add(Restrictions.eq("showType", command.getShowType()));
        }

        List<Order> orderList = new ArrayList();

        orderList.add(Order.desc("sendDate"));

        return messageRepository.pagination(command.getPage(), command.getPageSize(), criterionList, orderList);

    }

    @Override
    public Message delete(String messageId) {
        Message message = messageRepository.getById(messageId);
        message.setShowType(ShowType.BLANK);
        messageRepository.update(message);
        return message;
    }

    @Override
    public void companyCreate(CompanyCreateMessageCommand command) {
        //获取公司
        Company company=companyService.show(command.getCompany());
        //获取角色
        Role role = roleService.show(command.getUserRole());

        List<BaseUser> baseUsers = baseUserService.searchByUserRole(role.getId());

        for (BaseUser item : baseUsers) {
            Message message = new Message(company, item, new Date(), null, command.getContent(), command.getType(), ShowType.SHOW);
            messageRepository.save(message);
        }
    }

    @Override
    public Pagination<Message> pagination(CompanyListMessageCommand command) {
        List<Criterion> criterionList = new ArrayList();

        if(!CoreStringUtils.isEmpty(command.getCompany())){
            criterionList.add(Restrictions.like("content",command.getCompany(), MatchMode.ANYWHERE));
        }
        if (null != command.getShowType()) {
            criterionList.add(Restrictions.eq("showType", command.getShowType()));
        }

        List<Order> orderList = new ArrayList();

        orderList.add(Order.desc("sendDate"));

        return messageRepository.pagination(command.getPage(), command.getPageSize(), criterionList, orderList);

    }


}
