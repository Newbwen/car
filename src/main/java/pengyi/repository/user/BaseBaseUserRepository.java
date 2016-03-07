package pengyi.repository.user;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import pengyi.domain.model.user.BaseUser;
import pengyi.domain.model.user.IBaseUserRepository;
import pengyi.repository.generic.AbstractHibernateGenericRepository;

/**
 * Created by YJH on 2016/3/7.
 */
@Repository("userRepository")
public class BaseBaseUserRepository extends AbstractHibernateGenericRepository<BaseUser, String> implements IBaseUserRepository<BaseUser, String> {
    @Override
    public BaseUser getByPhone(String phone) {
        Criteria criteria = getSession().createCriteria(getPersistentClass());
        criteria.add(Restrictions.eq("phone", phone));
        return (BaseUser) criteria.uniqueResult();
    }
}