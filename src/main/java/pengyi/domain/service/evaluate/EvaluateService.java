package pengyi.domain.service.evaluate;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pengyi.domain.model.evaluate.Evaluate;
import pengyi.domain.model.evaluate.IEvaluateRepository;
import pengyi.domain.model.user.BaseUser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lvdi on 2016/3/7.
 */
@Service("evaluateService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class EvaluateService implements IEvaluateService {

    @Autowired
    private IEvaluateRepository evaluateRepository;

    /**
     * 保存
     */
    @Override
    @SuppressWarnings("unchecked")
    public void save(Evaluate evaluate) {
        Evaluate evaluate1=new Evaluate();
        evaluate.setEvaluateUser(evaluate.getEvaluateUser());
        evaluate.setOrder(evaluate.getOrder());
        evaluate.setContent(evaluate.getContent());
        evaluate.setLevel(evaluate.getLevel());
        evaluateRepository.save(evaluate1);

    }
    /**
     * 更新评价
     */
    @Override
    @SuppressWarnings("unchecked")
    public void upadte(Evaluate evaluate) {

        Evaluate evaluate1=new Evaluate();
        evaluate.setEvaluateUser(evaluate.getEvaluateUser());
        evaluate.setOrder(evaluate.getOrder());
        evaluate.setContent(evaluate.getContent());
        evaluate.setLevel(evaluate.getLevel());
        evaluateRepository.update(evaluate1);

    }
    /**
     * 根据评论人删除评论
     */
    @Override
    @SuppressWarnings("unchecked")
    public void delete(BaseUser evaluateUser) {

        Evaluate evaluate1=getEvaluateList(evaluateUser).get(0);
        evaluateRepository.delete(evaluate1);

    }
    /**
     * 根据评论人查询评论
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<Evaluate> getEvaluateList(BaseUser evaluateUser) {

        List<Order> orderList=new ArrayList<Order>();
        orderList.add(Order.desc("createDate"));

        List<Criterion> criterionList = new ArrayList<Criterion>();
        criterionList.add(Restrictions.eq("evaluateUser",evaluateUser));

        return evaluateRepository.list((Criterion[])criterionList.toArray(), (Order[]) orderList.toArray());

    }
    /**
     * 根据订单id查询评论
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<Evaluate> getEvaluateList(Order orderId) {

        List<Order> orderList=new ArrayList<Order>();
        orderList.add(Order.desc("createDate"));

        List<Criterion> criterionList = new ArrayList<Criterion>();
        criterionList.add(Restrictions.eq("order.id",orderId));

        return evaluateRepository.list((Criterion[])criterionList.toArray(), (Order[]) orderList.toArray());
    }
}
