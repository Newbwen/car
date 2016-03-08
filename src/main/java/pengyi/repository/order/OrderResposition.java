package pengyi.repository.order;

import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;
import pengyi.domain.model.user.IUserRepository;
import pengyi.repository.generic.AbstractHibernateGenericRepository;

/**
 * Author: lvdi
 * Date: 15-3-7
 */
@Repository("orderRepository")
public class OrderResposition extends AbstractHibernateGenericRepository<Order, String> implements IUserRepository<Order, String> {
}