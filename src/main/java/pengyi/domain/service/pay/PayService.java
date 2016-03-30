package pengyi.domain.service.pay;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pengyi.core.type.OrderStatus;
import pengyi.core.type.PayType;
import pengyi.domain.model.order.Order;
import pengyi.domain.model.pay.AlipayNotify;
import pengyi.domain.service.order.IOrderService;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by pengyi on 2016/3/30.
 */
@Service("payService")
public class PayService implements IPayService {

    @Autowired
    private IOrderService orderService;

    @Override
    public void alipaySuccess(AlipayNotify notify) {

        Order order = orderService.byOrderNumber(notify.getOut_trade_no());

        if (null != order && order.getExtraMoney().add(order.getShouldMoney()).equals(notify.getTotal_fee())) {
            order.setEndTime(new Date());
            order.setPayTime(notify.getGmt_payment());
            order.setOrderStatus(OrderStatus.SUCCESS);
            order.setPayType(PayType.ALIPAY);
            order.setPayNo(notify.getTrade_no());
        }

    }
}
