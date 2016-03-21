package pengyi.repository.billing;

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
}
