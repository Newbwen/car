package pengyi.domain.service.pay;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pengyi.core.type.OrderStatus;
import pengyi.core.type.PayType;
import pengyi.core.util.CoreDateUtils;
import pengyi.domain.model.order.Order;
import pengyi.domain.model.pay.AlipayNotify;
import pengyi.domain.model.pay.WechatNotify;
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

        orderService.paySuccress(order);
    }

    @Override
    public void wechatSuccess(WechatNotify notify) {

        Order order = orderService.byOrderNumber(notify.getOut_trade_no());

        if (null != order && 100*(order.getExtraMoney().add(order.getShouldMoney()).intValue()) == (notify.getTotal_fee())) {
            order.setEndTime(new Date());
            order.setPayTime(CoreDateUtils.parseDate(notify.getTime_end(), "yyyyMMddHHmmss"));
            order.setOrderStatus(OrderStatus.SUCCESS);
            order.setPayType(PayType.WECHAT);
            order.setPayNo(notify.getTransaction_id());
        }

        orderService.paySuccress(order);
    }
}
