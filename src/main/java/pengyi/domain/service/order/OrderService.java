package pengyi.domain.service.order;

import com.alibaba.fastjson.JSON;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pengyi.application.order.command.*;
import pengyi.core.commons.id.IdFactory;
import pengyi.core.exception.NoFoundException;
import pengyi.core.exception.OrderIsStartException;
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
import pengyi.socketserver.TcpService;

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

    @Autowired
    private IdFactory idFactory;

    @Override
    public Pagination<Order> pagination(ListOrderCommand command) {
        List<Criterion> criterionList = new ArrayList<Criterion>();

        List<org.hibernate.criterion.Order> orders = new ArrayList<org.hibernate.criterion.Order>();
        orders.add(org.hibernate.criterion.Order.desc("createDate"));

        if (!CoreStringUtils.isEmpty(command.getOrderNumber())) {
            criterionList.add(Restrictions.like("orderNumber", command.getOrderNumber(), MatchMode.ANYWHERE));
        }

        if (null != command.getOrderStatus()) {
            criterionList.add(Restrictions.eq("orderStatus", command.getOrderStatus()));
        }

        return orderRepository.pagination(command.getPage(), command.getPageSize(), criterionList, orders);
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
    public List<Order> searchByDriver(String driverId) {
        List<Criterion> criterionList = new ArrayList<Criterion>();
        criterionList.add(Restrictions.eq("receiveUser.id", driverId));
        return orderRepository.list(criterionList, null);
    }

    @Override
    public Order updateEvaluate(String orderId, EvaluateStatus evaluateStatus) {
        Order order = this.show(orderId);
        order.setEvaluateStatus(evaluateStatus);
        orderRepository.update(order);
        return order;
    }

    @Override
    public Pagination<Order> apiCompanyOrderPagination(CompanyOrderListCommand command) {
        List<Criterion> criterionList = new ArrayList<Criterion>();
        List<Driver> drivers = driverService.searchByCompany(command.getCompany());
        List<String> driverIds = new ArrayList<String>();
        for (Driver item : drivers) {
            driverIds.add(item.getId());
        }
        if (driverIds.size() > 0) {
            criterionList.add(Restrictions.in("receiveUser.id", driverIds.toArray()));
        } else {
            return new Pagination<Order>(new ArrayList<Order>(), 0, command.getPage(), command.getPageSize());
        }

        if (!CoreStringUtils.isEmpty(command.getOrderNumber())) {
            criterionList.add(Restrictions.like("orderNumber", command.getOrderNumber(), MatchMode.ANYWHERE));
        }

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

    @Override
    public Order apiCreateOrder(CreateOrderCommand command) {
        BaseUser orderUser = baseUserService.show(command.getOrderUser());

        String orderNo = idFactory.getNextId();

        Order order = new Order(orderNo, orderUser, new Date(), null, null,
                CoreDateUtils.parseDate(command.getSubscribeDate(), CoreDateUtils.DATETIME), null, command.getDriverType(),
                null, null, command.getExtraMoney(), null, OrderStatus.WAIT_ORDER, EvaluateStatus.NOT_EVALUATE,
                command.getStartAddress(), command.getEndAddress(), null);
        if (null != command.getCarType()) {
            order.setCarType(command.getCarType());
        }

        orderRepository.save(order);

        return order;
    }

    @Override
    public Order apiReceiverOrder(ReceiveOrderCommand command) {
        BaseUser receiveUser = baseUserService.show(command.getReceiveUser());
        Order order = this.show(command.getOrderId());
        order.fainWhenConcurrencyViolation(command.getVersion());

        order.setReceiveUser(receiveUser);
        order.setReceiveDate(new Date());
        order.setOrderStatus(OrderStatus.HAS_ORDER);

        orderRepository.update(order);

        String phone = order.getOrderUser().getUserName();
        if (TcpService.userClients.containsKey(phone)) {
            TcpService.userClients.get(phone).send(order.getOrderNumber());
        }
        return order;
    }

    @Override
    public Order apiStartOrder(UpDateOrderStatusCommand command) {
        Order order = this.show(command.getOrderId());
        order.fainWhenConcurrencyViolation(command.getVersion());

        order.setOrderStatus(OrderStatus.START_ORDER);
        order.setBeginTime(new Date());

        orderRepository.update(order);

        return order;
    }

    @Override
    public Order apiWaitPayOrder(UpDateOrderStatusCommand command) {
        Order order = this.show(command.getOrderId());
        order.fainWhenConcurrencyViolation(command.getVersion());

        order.setOrderStatus(OrderStatus.WAIT_PAY);

        orderRepository.update(order);

        return order;
    }

    @Override
    public Order apiPayOrder(UpDateOrderStatusCommand command) {
        Order order = this.show(command.getOrderId());
        order.fainWhenConcurrencyViolation(command.getVersion());

        order.setOrderStatus(OrderStatus.SUCCESS);

        orderRepository.update(order);

        return order;
    }

    @Override
    public Order apiCancelOrder(UpDateOrderStatusCommand command) {
        Order order = this.show(command.getOrderId());
        order.fainWhenConcurrencyViolation(command.getVersion());

        if (null != order.getReceiveUser()) {
            throw new OrderIsStartException("订单已经开始,不能取消订单!");
        }
        order.setOrderStatus(OrderStatus.INVALID);

        orderRepository.update(order);

        return order;
    }

    @Override
    public Pagination<Order> apiPagination(ListOrderCommand command) {
        List<Criterion> criterionList = new ArrayList<Criterion>();
        if (!CoreStringUtils.isEmpty(command.getOrderUser())) {
            criterionList.add(Restrictions.eq("orderUser.id", command.getOrderUser()));
        } else if (!CoreStringUtils.isEmpty(command.getReceiveUser())) {
            criterionList.add(Restrictions.eq("receiveUser.id", command.getReceiveUser()));
        }

        if (!CoreStringUtils.isEmpty(command.getOrderNumber())) {
            criterionList.add(Restrictions.eq("orderNumber", command.getOrderNumber()));
        }

        if (null != command.getOrderStatus()) {
            criterionList.add(Restrictions.eq("orderStatus", command.getOrderStatus()));
        }

        List<org.hibernate.criterion.Order> orderList = new ArrayList<org.hibernate.criterion.Order>();
        orderList.add(org.hibernate.criterion.Order.desc("createDate"));

        return orderRepository.pagination(command.getPage(), command.getPageSize(), criterionList, orderList);
    }
}
