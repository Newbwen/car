package pengyi.domain.service.evaluate;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pengyi.application.evaluate.command.CreateEvaluateCommand;
import pengyi.application.evaluate.command.EditEvaluateCommand;
import pengyi.application.evaluate.command.ListEvaluateCommand;
import pengyi.core.exception.NoFoundException;
import pengyi.core.util.CoreStringUtils;
import pengyi.domain.model.evaluate.Evaluate;
import pengyi.domain.model.evaluate.IEvaluateRepository;
import pengyi.domain.model.order.Order;
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


    @Override
    public Pagination<Evaluate> pagination(ListEvaluateCommand command) {
        List<Criterion> criteriaList = new ArrayList();
        if (!CoreStringUtils.isEmpty(command.getEvaluateUser())) {
            criteriaList.add(Restrictions.eq("evaluateUser.id", command.getEvaluateUser()));
        }
        if (!CoreStringUtils.isEmpty(command.getOrder())) {
            criteriaList.add(Restrictions.eq("order.id", command.getOrder()));
        }

        return evaluateRepository.pagination(command.getPage(), command.getPageSize(), criteriaList, null);
    }

    /**
     * 评价人修改评价
     */
    @Override
    public Evaluate edit(EditEvaluateCommand command) {
        Evaluate evaluate=this.show(command.getId());
//        Evaluate evaluate1=this.searchByOrder(command.getOrder());
//        BaseUser baseUser = baseUserService.show(command.getEvaluateUser());
//        Order order = orderService.show(command.getOrder());
////        TODO  获取订单信息
////        Order order=OrderSer
        evaluate.fainWhenConcurrencyViolation(command.getVersion());
        evaluate.setContent(command.getContent());
        evaluate.setLevel(command.getLevel());
        evaluateRepository.update(evaluate);

        return evaluate;

    }

    @Override
    public Evaluate show(String id) {

        Evaluate evaluate = (Evaluate) evaluateRepository.getById(id);
        if (null == evaluate) {
            throw new NoFoundException("没有找到资源路径id=[" + id + "]的记录");
        }
        return evaluate;
    }


    /**
     * 通过订单查看评价
     */
    @Override
    public Evaluate searchByOrder(String order) {
        return evaluateRepository.getByOrder(order);
    }


    /**
     * 发起评价
     *
     */
    @Override
    public Evaluate create(CreateEvaluateCommand command) {

        Order order = orderService.show(command.getOrder());
        BaseUser baseUser=baseUserService.searchByUserName(command.getEvaluateUser());
        Evaluate evaluate = new Evaluate(baseUser, order, command.getContent(), command.getLevel(), new Date());
        evaluateRepository.save(evaluate);
        return evaluate;
    }


}
