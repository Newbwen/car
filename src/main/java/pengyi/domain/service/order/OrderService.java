package pengyi.domain.service.order;

import com.alibaba.fastjson.JSON;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pengyi.application.billing.command.SearchBillingCommand;
import pengyi.application.moneydetailed.command.CreateMoneyDetailedCommand;
import pengyi.application.order.command.*;
import pengyi.core.commons.id.IdFactory;
import pengyi.core.exception.NoFoundException;
import pengyi.core.exception.NotSufficientFundsException;
import pengyi.core.exception.OrderIsStartException;
import pengyi.core.sms.service.SmsSender;
import pengyi.core.type.*;
import pengyi.core.util.CoreDateUtils;
import pengyi.core.util.CoreStringUtils;
import pengyi.domain.model.billing.Billing;
import pengyi.domain.model.order.IOrderRepository;
import pengyi.domain.model.order.Order;
import pengyi.domain.model.user.BaseUser;
import pengyi.domain.model.user.company.Company;
import pengyi.domain.model.user.driver.Driver;
import pengyi.domain.model.user.user.User;
import pengyi.domain.service.billing.IBillingService;
import pengyi.domain.service.moneydetailed.IMoneyDetailedService;
import pengyi.domain.service.user.IBaseUserService;
import pengyi.domain.service.user.company.ICompanyService;
import pengyi.domain.service.user.driver.IDriverService;
import pengyi.domain.service.user.user.IUserService;
import pengyi.repository.generic.Pagination;
import pengyi.socketserver.TcpService;

import java.math.BigDecimal;
import java.util.*;

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
    private IUserService userService;

    @Autowired
    private ICompanyService companyService;

    @Autowired
    private IBillingService billingService;

    @Autowired
    private IMoneyDetailedService moneyDetailedService;

    @Autowired
    private IdFactory idFactory;

    @Autowired
    private SmsSender smsSender;

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
        if (null != command.getDriverType()) {
            criterionList.add(Restrictions.eq("driverType", command.getDriverType()));
        }
        if (null != command.getCarType()) {
            criterionList.add(Restrictions.eq("carType", command.getCarType()));
        }
        if (!CoreStringUtils.isEmpty(command.getEndTime()) && !CoreStringUtils.isEmpty(command.getStartTime())) {
            criterionList.add(Restrictions.between("createDate", CoreDateUtils.parseDateStart(command.getStartTime()), CoreDateUtils.parseDateEnd(command.getEndTime())));
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
    public Order byOrderNumber(String orderNumber) {
        return orderRepository.byOrderNumber(orderNumber);
    }

    @Override
    public void paySuccress(Order order) {
        orderRepository.save(order);
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
        if (null != command.getDriverType()) {
            criterionList.add(Restrictions.eq("driverType", command.getDriverType()));
        }
        if (null != command.getCarType()) {
            criterionList.add(Restrictions.eq("carType", command.getCarType()));
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
                command.getStartAddress(), command.getEndAddress(), null, command.getStartLat(), command.getStartLon(), command.getEndLat(), command.getEndLon());
        if (null != command.getCarType()) {
            order.setCarType(command.getCarType());
        }

        orderRepository.save(order);

//        Order responseOrder = order;
//        orderUser.setUserRole(null);
//        responseOrder.setOrderUser(orderUser);

        String[] drivers = command.getDrivers().split(",");
        for (String driver : drivers) {
            if (TcpService.driverClients.containsKey(driver)) {
                TcpService.driverClients.get(driver).send(JSON.toJSONString(order));
            }
        }

        return order;
    }

    @Override
    public Order apiReceiverOrder(ReceiveOrderCommand command) {
        Driver driver = driverService.show(command.getReceiveUser());
        Order order = this.show(command.getOrderId());
        order.fainWhenConcurrencyViolation(command.getVersion());

        order.setReceiveUser(driver);
        order.setReceiveDate(new Date());
        order.setOrderStatus(OrderStatus.HAS_ORDER);

        orderRepository.update(order);

        String phone = order.getOrderUser().getUserName();

//        BaseUser orderUser = order.getOrderUser();
//        orderUser.setUserRole(null);
//        driver.setUserRole(null);
//        driver.setCompany(null);

//        order.setOrderUser(orderUser);
//        order.setReceiveUser(driver);
        sendToUser(phone, order);
        return order;
    }

    @Override
    public Order apiStartOrder(UpDateOrderStatusCommand command) {
        Order order = this.show(command.getOrderId());
        order.fainWhenConcurrencyViolation(command.getVersion());

        order.setOrderStatus(OrderStatus.START_ORDER);
        order.setBeginTime(new Date());

        orderRepository.update(order);

//        Order responseOrder = order;
//
        String phone = order.getOrderUser().getUserName();
//
//        BaseUser orderUser = order.getOrderUser();
//        orderUser.setUserRole(null);
//        Driver driver = (Driver) order.getReceiveUser();
//        driver.setUserRole(null);
//        driver.setCompany(null);
//
//        responseOrder.setOrderUser(orderUser);
//        responseOrder.setReceiveUser(driver);

        sendToUser(phone, order);

        return order;
    }

    @Override
    public Order apiWaitPayOrder(UpDateOrderStatusCommand command) {
        Order order = this.show(command.getOrderId());
        order.fainWhenConcurrencyViolation(command.getVersion());

        order.setKm(command.getKm());
        order.setEndTime(new Date());

        if (DriverType.TAXI == order.getDriverType()) {
            order.setOrderStatus(OrderStatus.SUCCESS);
        } else {
            Driver driver = driverService.show(order.getReceiveUser().getId());
            SearchBillingCommand billingCommand = new SearchBillingCommand();
            billingCommand.setUserName(driver.getUserName());
            billingCommand.setDriverType(order.getDriverType());
            if (null != order.getCarType()) {
                billingCommand.setCarType(order.getCarType());
            }
            List<Billing> billingList = billingService.searchByDriver(billingCommand);
            if (null == billingList || billingList.size() < 1) {
                throw new NoFoundException("没有找到计费模板");
            }
            Billing billing = billingList.get(0);
            BigDecimal knMoney;
            if (command.getKm() > billing.getStartKm()) {
                knMoney = billing.getKmBilling().multiply(new BigDecimal(command.getKm() - billing.getStartKm()));
            } else {
                knMoney = new BigDecimal(0);
            }

            long dateTime = (order.getEndTime().getTime() - order.getBeginTime().getTime());
            dateTime = dateTime % 60000 == 0 ? (dateTime / 60000) : (dateTime / 60000) + 1;
            BigDecimal minuteMoney;
            if (dateTime > billing.getStartMin()) {
                minuteMoney = billing.getMinuteBilling().multiply(new BigDecimal(dateTime - billing.getStartMin()));
            } else {
                minuteMoney = new BigDecimal(0);
            }

            order.setShouldMoney(knMoney.add(minuteMoney).add(billing.getStartingPrice()));
            order.setOrderStatus(OrderStatus.WAIT_PAY);
        }
        orderRepository.update(order);

//        Order responseOrder = order;
//
        String phone = order.getOrderUser().getUserName();
//
//        BaseUser orderUser = order.getOrderUser();
//        orderUser.setUserRole(null);
//        driver.setUserRole(null);
//        driver.setCompany(null);
//
//        responseOrder.setOrderUser(orderUser);
//        responseOrder.setReceiveUser(driver);

        sendToUser(phone, order);

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

        if (order.getOrderStatus() != OrderStatus.WAIT_ORDER && order.getOrderStatus() != OrderStatus.HAS_ORDER) {
            throw new OrderIsStartException("订单已经开始,不能取消订单!");
        }
        order.setOrderStatus(OrderStatus.INVALID);

        orderRepository.update(order);

        if (null != order.getReceiveUser()) {

//            Order responseOrder = order;

            String phone = order.getReceiveUser().getUserName();

//            BaseUser orderUser = order.getOrderUser();
//            orderUser.setUserRole(null);
//            Driver driver = (Driver) order.getReceiveUser();
//
//            driver.setUserRole(null);
//            driver.setCompany(null);

//            responseOrder.setOrderUser(orderUser);
//            responseOrder.setReceiveUser(driver);

            sendToDriver(phone, order);
        }

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

        if (null != command.getDriverType()) {
            criterionList.add(Restrictions.eq("driverType", command.getDriverType()));
        }

        if (null != command.getCarType()) {
            criterionList.add(Restrictions.eq("carType", command.getCarType()));
        }
        if (!CoreStringUtils.isEmpty(command.getEndTime()) && !CoreStringUtils.isEmpty(command.getStartTime())) {
            criterionList.add(Restrictions.between("createDate", CoreDateUtils.parseDateStart(command.getStartTime()), CoreDateUtils.parseDateEnd(command.getEndTime())));
        }
        List<org.hibernate.criterion.Order> orderList = new ArrayList<org.hibernate.criterion.Order>();
        orderList.add(org.hibernate.criterion.Order.desc("createDate"));

        return orderRepository.pagination(command.getPage(), command.getPageSize(), criterionList, orderList);
    }

    @Override
    public Order balancePay(BalancePayCommand command) {
        //资金明细
        CreateMoneyDetailedCommand moneyDetailedCommand = new CreateMoneyDetailedCommand();

        Order order = this.show(command.getOrderId());
        order.fainWhenConcurrencyViolation(command.getVersion());
        User user = userService.show(order.getOrderUser().getId());
        Driver driver = driverService.show(order.getReceiveUser().getId());
        Company company = driver.getCompany();

        if (user.getBalance().compareTo(order.getShouldMoney()) == -1) {
            throw new NotSufficientFundsException("用户余额不足");
        }

        moneyDetailedCommand.setOldMoney(user.getBalance());//设置资金明细原有金额
//        userService.addLock();
        user.setBalance(user.getBalance().subtract(order.getShouldMoney()));
        userService.update(user);
//        companyService.addLock();
        company.setBalance(company.getBalance().add(order.getShouldMoney()));
        companyService.update(company);

        order.setPayTime(new Date());
        order.setOrderStatus(OrderStatus.SUCCESS);
        order.setPayType(PayType.BALANCE);

        orderRepository.update(order);

        //创建资金明细
        moneyDetailedCommand.setBaseUser(order.getOrderUser().getId());
        moneyDetailedCommand.setFlowType(FlowType.OUT_FLOW);
        moneyDetailedCommand.setMoney(order.getShouldMoney());
        moneyDetailedCommand.setExplain("订单支付:" + order.getOrderNumber());
        moneyDetailedCommand.setNewMoney(user.getBalance());
        moneyDetailedService.create(moneyDetailedCommand);

        String phone = order.getReceiveUser().getUserName();

//        Order responseOrder = order;

//        BaseUser orderUser = order.getOrderUser();
//        orderUser.setUserRole(null);
//        driver.setUserRole(null);
//        driver.setCompany(null);
//
//        responseOrder.setOrderUser(orderUser);
//        responseOrder.setReceiveUser(driver);

        sendToDriver(phone, order);

        return order;
    }

    @Override
    public Order offLinePay(OffLinePayCommand command) {

        //资金明细
        CreateMoneyDetailedCommand moneyDetailedCommand = new CreateMoneyDetailedCommand();

        Order order = this.show(command.getOrderId());
        order.fainWhenConcurrencyViolation(command.getVersion());

        Driver driver = driverService.show(order.getReceiveUser().getId());
        Company company = companyService.show(driver.getCompany().getId());

        moneyDetailedCommand.setOldMoney(driver.getBalance());
//        driverService.addLock();
        driver.setBalance(driver.getBalance().subtract(order.getShouldMoney()));
        driverService.update(driver);
//        companyService.addLock();
        company.setBalance(company.getBalance().add(order.getShouldMoney()));
        companyService.update(company);

        order.setPayTime(new Date());
        order.setOrderStatus(OrderStatus.SUCCESS);
        order.setPayType(PayType.OFFLINE);

        orderRepository.update(order);
        //创建资金明细
        moneyDetailedCommand.setBaseUser(order.getReceiveUser().getId());
        moneyDetailedCommand.setFlowType(FlowType.OUT_FLOW);
        moneyDetailedCommand.setMoney(order.getShouldMoney());
        moneyDetailedCommand.setExplain("订单支付:" + order.getOrderNumber());
        moneyDetailedCommand.setNewMoney(driver.getBalance());
        moneyDetailedService.create(moneyDetailedCommand);

        String phone = order.getOrderUser().getUserName();

//        Order responseOrder = order;
//
//        BaseUser orderUser = order.getOrderUser();
//        orderUser.setUserRole(null);
//        driver.setUserRole(null);
//        driver.setCompany(null);
//
//        responseOrder.setOrderUser(orderUser);
//        responseOrder.setReceiveUser(driver);

        sendToUser(phone, order);
        return order;
    }

    @Override
    public boolean hasOrder(String userId, String orderId) {

        Order order = orderRepository.getById(orderId);

        if (null == order.getSubscribeDate()) {
            List<Criterion> criterionList = new ArrayList<Criterion>();
            criterionList.add(Restrictions.eq("receiveUser.id", userId));
            criterionList.add(Restrictions.isNull("subscribeDate"));

            List<OrderStatus> orderStatuses = new ArrayList<OrderStatus>();
            orderStatuses.add(OrderStatus.HAS_ORDER);
            orderStatuses.add(OrderStatus.START_ORDER);
            criterionList.add(Restrictions.in("orderStatus", orderStatuses));
            List<Order> orders = orderRepository.list(criterionList, null);
            return null != orders && orders.size() != 0;
        }

        return false;
    }

    @Override
    public boolean hasBalance(String userId) {
        BaseUser baseUser = baseUserService.show(userId);
        return baseUser.getBalance().doubleValue() >= 0;
    }

    @Override
    public List<Order> exportExcel(ListOrderCommand command) {
        List<Criterion> criterionList = new ArrayList<Criterion>();
        Map<String, String> alias = new HashMap<String, String>();
        if (!CoreStringUtils.isEmpty(command.getOrderUser())) {
            criterionList.add(Restrictions.like("orderUser.userName", command.getOrderUser(), MatchMode.ANYWHERE));
            alias.put("orderUser", "orderUser");
        }
        if (!CoreStringUtils.isEmpty(command.getReceiveUser())) {
            criterionList.add(Restrictions.like("receiveUser.userName", command.getReceiveUser(), MatchMode.ANYWHERE));
            alias.put("receiveUser", "receiveUser");
        }

        if (!CoreStringUtils.isEmpty(command.getOrderNumber())) {
            criterionList.add(Restrictions.eq("orderNumber", command.getOrderNumber()));
        }

        if (null != command.getOrderStatus()) {
            criterionList.add(Restrictions.eq("orderStatus", command.getOrderStatus()));
        }

        if (null != command.getDriverType()) {
            criterionList.add(Restrictions.eq("driverType", command.getDriverType()));
        }

        if (null != command.getCarType()) {
            criterionList.add(Restrictions.eq("carType", command.getCarType()));
        }
        if (!CoreStringUtils.isEmpty(command.getEndTime()) && !CoreStringUtils.isEmpty(command.getStartTime())) {
            criterionList.add(Restrictions.between("createDate", CoreDateUtils.parseDateStart(command.getStartTime()), CoreDateUtils.parseDateEnd(command.getEndTime())));
        }
        List<org.hibernate.criterion.Order> orderList = new ArrayList<org.hibernate.criterion.Order>();
        orderList.add(org.hibernate.criterion.Order.desc("createDate"));
        return orderRepository.list(criterionList, orderList, null, null, alias);
    }

    @Override
    public List<Order> apiExportExcel(CompanyOrderListCommand command) {
        List<Criterion> criterionList = new ArrayList<Criterion>();
        Map<String, String> alias = new HashMap<String, String>();

        List<Driver> drivers = driverService.searchByCompany(command.getCompany());
        List<String> driverIds = new ArrayList<String>();
        for (Driver item : drivers) {
            driverIds.add(item.getId());
        }
        if (driverIds.size() > 0) {
            criterionList.add(Restrictions.in("receiveUser.id", driverIds.toArray()));
        } else {
            return null;
        }

        if (!CoreStringUtils.isEmpty(command.getOrderUser())) {
            criterionList.add(Restrictions.like("orderUser.userName", command.getOrderUser(), MatchMode.ANYWHERE));
            alias.put("orderUser", "orderUser");
        }
        if (!CoreStringUtils.isEmpty(command.getReceiveUser())) {
            criterionList.add(Restrictions.like("receiveUser.userName", command.getReceiveUser(), MatchMode.ANYWHERE));
            alias.put("receiveUser", "receiveUser");
        }

        if (!CoreStringUtils.isEmpty(command.getOrderNumber())) {
            criterionList.add(Restrictions.eq("orderNumber", command.getOrderNumber()));
        }

        if (null != command.getOrderStatus()) {
            criterionList.add(Restrictions.eq("orderStatus", command.getOrderStatus()));
        }

        if (null != command.getDriverType()) {
            criterionList.add(Restrictions.eq("driverType", command.getDriverType()));
        }

        if (null != command.getCarType()) {
            criterionList.add(Restrictions.eq("carType", command.getCarType()));
        }
        if (!CoreStringUtils.isEmpty(command.getStartCreateDate()) && !CoreStringUtils.isEmpty(command.getEndCreateDate())) {
            criterionList.add(Restrictions.between("createDate", CoreDateUtils.parseDateStart(command.getStartCreateDate()), CoreDateUtils.parseDateEnd(command.getEndCreateDate())));
        }
        List<org.hibernate.criterion.Order> orderList = new ArrayList<org.hibernate.criterion.Order>();
        orderList.add(org.hibernate.criterion.Order.desc("createDate"));
        return orderRepository.list(criterionList, orderList, null, null, alias);
    }

    @Override
    public Order driverCreateOrder(DriverCreateOrderCommand command) {
        Driver driver = driverService.show(command.getDriverId());
        User user = (User) baseUserService.searchByUserName(command.getUserName());
        String orderNo = idFactory.getNextId();
        Order order = new Order(orderNo, user, new Date(), driver, new Date(), null, new Date(), DriverType.GENERATION,
                null, null, null, null, OrderStatus.START_ORDER, EvaluateStatus.NOT_EVALUATE, command.getStartAddress(), null, null, 0.0, 0.0, 0.0, 0.0);
        orderRepository.save(order);
        //TODO 发送短信
//        smsSender.send(user.getUserName());
        return order;
    }

    private void sendToUser(String phone, Order order) {
        if (TcpService.userClients.containsKey(phone)) {
            if (!TcpService.userClients.get(phone).send(JSON.toJSONString(order))) {
                putUserMessage(phone, order);
            }
        } else {
            putUserMessage(phone, order);
        }
    }

    private void sendToDriver(String phone, Order order) {
        if (TcpService.driverClients.containsKey(phone)) {
            if (!TcpService.driverClients.get(phone).send(JSON.toJSONString(order))) {
                putDriverMessage(phone, order);
            }
        } else {
            putDriverMessage(phone, order);
        }
    }

    private void putUserMessage(String phone, Order order) {
        if (TcpService.userMessages.containsKey(phone)) {
            List<String> messages = TcpService.userMessages.get(phone);
            messages.add(JSON.toJSONString(order));
            TcpService.userMessages.replace(phone, messages);
        } else {
            List<String> messages = new ArrayList<String>();
            messages.add(JSON.toJSONString(order));
            TcpService.userMessages.put(phone, messages);
        }
    }

    private void putDriverMessage(String phone, Order order) {
        if (TcpService.driverMessages.containsKey(phone)) {
            List<String> messages = TcpService.driverMessages.get(phone);
            messages.add(JSON.toJSONString(order));
            TcpService.driverMessages.replace(phone, messages);
        } else {
            List<String> messages = new ArrayList<String>();
            messages.add(JSON.toJSONString(order));
            TcpService.driverMessages.put(phone, messages);
        }
    }
}
