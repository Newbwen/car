package pengyi.repository.billing;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import pengyi.domain.model.billing.Billing;
import pengyi.domain.model.billing.IBillingRepository;
import pengyi.repository.generic.AbstractHibernateGenericRepository;

/**
 * Created by YJH on 2016/3/21.
 */
@Repository("billingRepository")
public class BillingRepository extends AbstractHibernateGenericRepository<Billing, String>
        implements IBillingRepository<Billing, String> {
    @Override
    public Billing searchByArea(String areaId) {
        Criteria criteria = getSession().createCriteria(getPersistentClass());
        criteria.add(Restrictions.eq("area.id", areaId));
        return (Billing) criteria.uniqueResult();
    }
}
