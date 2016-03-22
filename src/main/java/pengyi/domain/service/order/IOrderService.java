package pengyi.domain.service.order;

import pengyi.application.order.command.*;
import pengyi.core.type.EvaluateStatus;
import pengyi.domain.model.order.Order;
import pengyi.domain.model.user.BaseUser;
import pengyi.repository.generic.Pagination;

import java.util.List;

/**
 * Created by YJH on 2016/3/8.
 */
public interface IOrderService {

    Pagination<Order> pagination(ListOrderCommand command);

    Order show(String id);

    List<Order> searchByDriver(String driverId);

    Order updateEvaluate(String orderId, EvaluateStatus evaluateStatus);

    /********
     * api 方法
     ***********/
    Pagination<Order> apiCompanyOrderPagination(CompanyOrderListCommand command);

    Order apiCreateOrder(CreateOrderCommand command);

    Order apiReceiverOrder(ReceiveOrderCommand command);

    Order apiStartOrder(UpDateOrderStatusCommand command);

    Order apiWaitPayOrder(UpDateOrderStatusCommand command);

    Order apiPayOrder(UpDateOrderStatusCommand command);

    Order apiCancelOrder(UpDateOrderStatusCommand command);

    Pagination<Order> apiPagination(ListOrderCommand command);

}
