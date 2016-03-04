package pengyi.repository.user;

import org.springframework.stereotype.Repository;
import pengyi.domain.model.user.BaseUser;
import pengyi.domain.model.user.IUserRepository;
import pengyi.repository.generic.AbstractHibernateGenericRepository;

/**
 * Author: pengyi
 * Date: 15-12-30
 */
@Repository("userRepository")
public class UserResposition extends AbstractHibernateGenericRepository<BaseUser, String> implements IUserRepository<BaseUser, String> {
}