package pengyi.repository.evaluate;

import org.springframework.stereotype.Repository;
import pengyi.domain.model.evaluate.IEvaluateRepository;
import pengyi.domain.model.user.BaseUser;
import pengyi.domain.model.user.IUserRepository;
import pengyi.repository.generic.AbstractHibernateGenericRepository;

/**
 * Author: lvdi
 * Date: 15-3-7
 */
@Repository("evaluateRepository")
public class EvaluateResposition extends AbstractHibernateGenericRepository<BaseUser, String> implements IEvaluateRepository<BaseUser, String> {
}