package pengyi.domain.service.order;

import pengyi.application.order.command.CompanyOrderListCommand;
import pengyi.application.order.command.CreateOrderCommand;
import pengyi.application.order.command.ListOrderCommand;
import pengyi.application.order.command.UpDateOrderStatusCommand;
import pengyi.domain.model.order.Order;
import pengyi.repository.generic.Pagination;

/**
 * Created by YJH on 2016/3/8.
 */
public interface IOrderService {

    Pagination<Order> pagination(ListOrderCommand command);

    Order updateOrderStatus(UpDateOrderStatusCommand command);

    Order show(String id);

    /********  api 方法 ***********/
    Pagination<Order> apiPagination(CompanyOrderListCommand command);

    Order apiCreateOrder(CreateOrderCommand command);
}
