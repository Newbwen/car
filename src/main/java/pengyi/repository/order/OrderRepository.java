package pengyi.repository.order;

import org.springframework.stereotype.Repository;
import pengyi.domain.model.order.IOrderRepository;
import pengyi.domain.model.order.Order;
import pengyi.repository.generic.AbstractHibernateGenericRepository;

/**
 * Created by YJH on 2016/3/8.
 */
@Repository("orderRepository")
public class OrderRepository extends AbstractHibernateGenericRepository<Order, String>
        implements IOrderRepository<Order, String> {
}
