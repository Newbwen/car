package pengyi.repository.evaluate;

import org.springframework.stereotype.Repository;
import pengyi.domain.model.evaluate.Evaluate;
import pengyi.domain.model.evaluate.IEvaluateRepository;
import pengyi.repository.generic.AbstractHibernateGenericRepository;


/**
 * Author: lvdi
 * Date: 15-3-7
 */
@Repository("evaluateRepository")
public class EvaluateResposition extends AbstractHibernateGenericRepository<Evaluate, String> implements IEvaluateRepository<Evaluate, String> {
}