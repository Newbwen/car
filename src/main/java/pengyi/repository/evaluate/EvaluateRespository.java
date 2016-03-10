package pengyi.repository.evaluate;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import pengyi.domain.model.evaluate.Evaluate;
import pengyi.domain.model.evaluate.IEvaluateRepository;
import pengyi.repository.generic.AbstractHibernateGenericRepository;


/**
 * Author: lvdi
 * Date: 15-3-7
 */
@Repository("evaluateRepository")
public class EvaluateRespository extends AbstractHibernateGenericRepository<Evaluate, String> implements IEvaluateRepository<Evaluate, String> {
    @Override
    public Evaluate getByName(String evaluateUser) {

        Criteria criteria=getSession().createCriteria(getPersistentClass());
        criteria.add(Restrictions.eq("evaluateUser",evaluateUser));
        return (Evaluate) criteria.uniqueResult();
    }


}