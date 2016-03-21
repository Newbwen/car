package pengyi.domain.model.billing;

import pengyi.repository.generic.IHibernateGenericRepository;

import java.io.Serializable;

/**
 * Created by YJH on 2016/3/21.
 */
public interface IBillingRepository<T, ID extends Serializable> extends IHibernateGenericRepository<T, ID> {
}
