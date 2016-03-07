package pengyi.domain.service.evaluate;

import org.hibernate.criterion.Order;
import pengyi.domain.model.evaluate.Evaluate;
import pengyi.domain.model.user.BaseUser;

import java.util.List;

/**
 * Created by pengyi on 2015/12/24.
 */
public interface IEvaluateService {

    void save(Evaluate evaluate);

    void upadte(Evaluate evaluate);

    void delete(BaseUser evaluateUser);

    List<Evaluate> getEvaluateList(BaseUser evaluateUser);

    List<Evaluate> getEvaluateList(Order orderId);

}
