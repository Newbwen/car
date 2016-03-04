package pengyi.domain.model.user;

import pengyi.repository.generic.IHibernateGenericRepository;

import java.io.Serializable;

/**
 * Created by pengyi on 2015/12/24.
 */

public interface IUserRepository<T, ID extends Serializable> extends IHibernateGenericRepository<T, ID> {
}
