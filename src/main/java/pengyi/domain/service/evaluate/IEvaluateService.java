package pengyi.domain.service.evaluate;

import org.hibernate.criterion.Order;
import pengyi.domain.model.evaluate.Evaluate;
import pengyi.domain.model.user.BaseUser;
import pengyi.repository.generic.Pagination;


/**
 * Created by lvdi on 2016/3/8.
 */
public interface IEvaluateService {

    void save(Evaluate evaluate);

    void upadte(Evaluate evaluate);

    void delete(int evaluateId);

    Pagination<Evaluate> getEvaluateList(BaseUser evaluateUser, int page, int pageSize);

    Pagination<Evaluate> getEvaluateList(Order orderId, int page, int pageSize);

    Evaluate getById(int evaluateId);


}
