package pengyi.domain.model.user.driver;

import pengyi.repository.generic.IHibernateGenericRepository;

import java.io.Serializable;

/**
 * Created by YJH on 2016/3/7.
 */
public interface IDriverRepository<T, ID extends Serializable> extends IHibernateGenericRepository<T, ID> {
}
