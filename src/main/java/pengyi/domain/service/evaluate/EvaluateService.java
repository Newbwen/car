package pengyi.domain.service.evaluate;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pengyi.application.evaluate.command.CreateEvaluateCommand;
import pengyi.application.evaluate.command.EditEvaluateCommand;
import pengyi.core.exception.ExistException;
import pengyi.core.exception.NoFoundException;
import pengyi.domain.model.evaluate.Evaluate;
import pengyi.domain.model.evaluate.IEvaluateRepository;
import pengyi.domain.model.permission.Permission;
import pengyi.domain.model.urlresources.UrlResources;
import pengyi.domain.model.user.BaseUser;
import pengyi.domain.service.order.OrderService;
import pengyi.domain.service.user.BaseUserService;
import pengyi.repository.generic.Pagination;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by lvdi on 2016/3/8.
 */
@Service("evaluateService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class EvaluateService implements IEvaluateService {

    @Autowired
    private IEvaluateRepository evaluateRepository;

    @Autowired
    private BaseUserService baseUserService;

    @Autowired
    private OrderService orderService;

    /**
     * 保存
     */
    @Override
    @SuppressWarnings("unchecked")
    public void save(Evaluate evaluate) {

        Evaluate evaluate1 = new Evaluate();
        evaluate.setEvaluateUser(evaluate.getEvaluateUser());
        evaluate.setOrder(evaluate.getOrder());
        evaluate.setContent(evaluate.getContent());
        evaluate.setLevel(evaluate.getLevel());

        evaluateRepository.save(evaluate1);

    }

    @Override
    public Evaluate create(EditEvaluateCommand command) {

        Evaluate evaluate=this.show(command.getId());
        BaseUser baseUser = baseUserService.show(command.getEvaluateUser());
//        TODO  获取订单信息
//        Order order=OrderServ
        Evaluate evaluate1=new Evaluate(baseUser,null,command.getLevel(),command.getContent(),new Date());
        evaluateRepository.save(evaluate1);

        return evaluate1;

    }

    /**
     * 更新评价
     */
    @Override
    @SuppressWarnings("unchecked")
    public void update(Evaluate evaluate) {

        Evaluate evaluate1 = new Evaluate();

        evaluate.setEvaluateUser(evaluate.getEvaluateUser());
        evaluate.setOrder(evaluate.getOrder());
        evaluate.setContent(evaluate.getContent());
        evaluate.setLevel(evaluate.getLevel());

        evaluateRepository.update(evaluate1);

    }

    /**
     * 根据评论Id删除评论
     */
    @Override
    @SuppressWarnings("unchecked")
    public void delete(int evaluateId) {

        Evaluate evaluate = getById(evaluateId);

        evaluateRepository.delete(evaluate);
    }


    /**
     * 根据评论人查询评论
     */
    @Override
    @SuppressWarnings("unchecked")
    public Pagination<Evaluate> getByUser(String evaluateUserId, int page, int pageSize) {

        List<Order> orderList = new ArrayList<Order>();
        orderList.add(Order.desc("createDate"));

        List<Criterion> criterionList = new ArrayList<Criterion>();
        criterionList.add(Restrictions.eq("evaluateUser.id", evaluateUserId));

        return evaluateRepository.pagination(page, pageSize, criterionList, orderList);

    }

    /**
     * 根据订单id查询评论
     */
    @Override
    @SuppressWarnings("unchecked")
    public Pagination<Evaluate> getByOrder(String orderId, int page, int pageSize) {

        List<Order> orderList = new ArrayList<Order>();
        orderList.add(Order.desc("createDate"));

        List<Criterion> criterionList = new ArrayList<Criterion>();
        criterionList.add(Restrictions.eq("order.id", orderId));

        return evaluateRepository.pagination(page, pageSize, criterionList, orderList);
    }

    /**
     * 根据评论id查询评论
     */
    @Override
    @SuppressWarnings("unchecked")
    public Evaluate getById(int evaluateId) {

        return (Evaluate) evaluateRepository.getById(evaluateId);
    }

    @Override
    public Evaluate edit(EditEvaluateCommand command) {

        Evaluate evaluate = this.show(command.getId());
        if (!evaluate.getEvaluateUser().equals(command.getEvaluateUser())) {
            if (null != this.searchByName(command.getEvaluateUser())) {
                throw new ExistException("评价名[" + command.getEvaluateUser() + "]的记录已存在");

            }
        }

        BaseUser baseUser = baseUserService.show(command.getEvaluateUser());
//        TODO  获取订单信息
//        Order order=OrderServ
        Evaluate evaluate1=new Evaluate(baseUser,null,command.getLevel(),command.getContent(),new Date());
        evaluateRepository.save(evaluate1);

        return evaluate1;

    }

    @Override
    public Evaluate show(String id) {

        Evaluate evaluate = (Evaluate) evaluateRepository.getById(id);
        if (null == evaluate) {
            throw new NoFoundException("没有找到资源路径id=[" + id + "]的记录");
        }
        return evaluate;
    }

    @Override
    public Evaluate searchByName(String evaluateUserame) {

        return evaluateRepository.getByName(evaluateUserame);
    }

    @Override
    public Evaluate create(CreateEvaluateCommand command) {
        return null;
    }
}
