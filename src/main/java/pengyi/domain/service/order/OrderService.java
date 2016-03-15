package pengyi.domain.service.order;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pengyi.application.order.command.CompanyOrderListCommand;
import pengyi.application.order.command.CreateOrderCommand;
import pengyi.application.order.command.ListOrderCommand;
import pengyi.application.order.command.UpDateOrderStatusCommand;
import pengyi.core.exception.NoFoundException;
import pengyi.core.type.EvaluateStatus;
import pengyi.core.type.OrderStatus;
import pengyi.core.util.CoreDateUtils;
import pengyi.core.util.CoreStringUtils;
import pengyi.domain.model.order.IOrderRepository;
import pengyi.domain.model.order.Order;
import pengyi.domain.model.user.BaseUser;
import pengyi.domain.model.user.driver.Driver;
import pengyi.domain.service.user.IBaseUserService;
import pengyi.domain.service.user.driver.IDriverService;
import pengyi.repository.generic.Pagination;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by YJH on 2016/3/8.
 */
@Service("orderService")
public class OrderService implements IOrderService {

    @Autowired
    private IOrderRepository<Order, String> orderRepository;

    @Autowired
    private IBaseUserService baseUserService;

    @Autowired
    private IDriverService driverService;

    @Override
    public Pagination<Order> pagination(ListOrderCommand command) {
        List<Criterion> criterions = new ArrayList<Criterion>();

        List<org.hibernate.criterion.Order> orders = new ArrayList<org.hibernate.criterion.Order>();
        orders.add(org.hibernate.criterion.Order.desc("createDate"));

        return orderRepository.pagination(command.getPage(), command.getPageSize(), criterions, orders);
    }

    @Override
    public Order create(CreateOrderCommand command) {

        String orderNumber = UUID.randomUUID().toString();//TODO 订单号生成
        BaseUser orderUser = baseUserService.show(command.getOrderUser());
        BaseUser receiveUser = baseUserService.show(command.getReceiveUser());

        Order order = new Order(orderNumber, orderUser, new Date(), receiveUser, null, command.getSubscribeDate(),
                null, command.getDriverType(), null, command.getShouldMoney(), null, null, OrderStatus.WAIT_ORDER,
                EvaluateStatus.NOT_EVALUATE);

        orderRepository.save(order);

        return order;
    }

    @Override
    public Order updateOrderStatus(UpDateOrderStatusCommand command) {
        Order order = this.show(command.getId());

        order.fainWhenConcurrencyViolation(command.getVersion());

        order.setOrderStatus(command.getStatus());

        orderRepository.update(order);

        return order;
    }

    @Override
    public Order show(String id) {
        Order order = orderRepository.getById(id);
        if (null == order) {
            throw new NoFoundException("没有找到订单id=[" + id + "]的记录");
        }
        return order;
    }

    @Override
    public Pagination<Order> apiPagination(CompanyOrderListCommand command) {
        List<Criterion> criterionList = new ArrayList<Criterion>();
        List<Driver> drivers = driverService.searchByCompany(command.getCompany());
        List<String> driverIds = new ArrayList<String>();
        for (Driver item : drivers) {
            driverIds.add(item.getId());
        }
        criterionList.add(Restrictions.in("receiveUser.id", driverIds.toArray()));

        if (null != command.getOrderStatus()) {
            criterionList.add(Restrictions.eq("orderStatus", command.getOrderStatus()));
        }

        if (!CoreStringUtils.isEmpty(command.getStartCreateDate())) {
            criterionList.add(Restrictions.ge("createDate", CoreDateUtils.parseDate(command.getStartCreateDate())));
        }
        if (!CoreStringUtils.isEmpty(command.getEndCreateDate())) {
            criterionList.add(Restrictions.le("createDate", CoreDateUtils.parseDate(command.getEndCreateDate())));
        }

        List<org.hibernate.criterion.Order> orderList = new ArrayList<org.hibernate.criterion.Order>();
        orderList.add(org.hibernate.criterion.Order.desc("createDate"));

        return orderRepository.pagination(command.getPage(), command.getPageSize(), criterionList, orderList);
    }
}
