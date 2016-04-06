package pengyi.domain.service.pay;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;
import pengyi.core.commons.Constants;
import pengyi.core.exception.WechatSignException;
import pengyi.core.pay.wechat.UnifiedRequest;
import pengyi.core.pay.wechat.UnifiedResponse;
import pengyi.core.type.OrderStatus;
import pengyi.core.type.PayType;
import pengyi.core.util.CoreDateUtils;
import pengyi.core.util.HttpUtil;
import pengyi.core.util.Signature;
import pengyi.core.util.XMLParser;
import pengyi.domain.model.order.Order;
import pengyi.domain.model.pay.AlipayNotify;
import pengyi.domain.model.pay.WechatNotify;
import pengyi.domain.service.order.IOrderService;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.logging.Logger;

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
            order.setPayTime(CoreDateUtils.parseLongDate(notify.getGmt_payment()));
            order.setOrderStatus(OrderStatus.SUCCESS);
            order.setPayType(PayType.ALIPAY);
            order.setPayNo(notify.getTrade_no());
        }

        orderService.paySuccress(order);
    }

    @Override
    public void wechatSuccess(WechatNotify notify) {

        Order order = orderService.byOrderNumber(notify.getOut_trade_no());

        if (null != order && 100 * (order.getExtraMoney().add(order.getShouldMoney()).intValue()) == (notify.getTotal_fee())) {
            order.setEndTime(new Date());
            order.setPayTime(CoreDateUtils.parseDate(notify.getTime_end(), "yyyyMMddHHmmss"));
            order.setOrderStatus(OrderStatus.SUCCESS);
            order.setPayType(PayType.WECHAT);
            order.setPayNo(notify.getTransaction_id());
        }

        orderService.paySuccress(order);
    }

    @Override
    public UnifiedResponse wechatPay(String orderId, String ipAddress) {

        Order order = orderService.show(orderId);
        String body = "订单号：" + order.getOrderNumber() + "，" + order.getDriverType().getName();
        String detail = order.getStartAddress() + "到" + order.getEndAddress();
        UnifiedRequest request = new UnifiedRequest(body, detail, order.getOrderNumber(), 100 * (order.getShouldMoney().add(order.getExtraMoney()).intValue()), ipAddress);

        try {
            String sign = Signature.getSign(request.toMap());
            request.setSign(sign);
            XStream xStream = new XStream(new DomDriver("utf-8", new XmlFriendlyNameCoder("-_", "_")));
            String s = HttpUtil.urlConnection(Constants.WECHAT_UNIFIED_URL, xStream.toXML(request));
            String st = Signature.getSignFromResponseString(s);
            UnifiedResponse response = null;
            response = (UnifiedResponse) XMLParser.getObjFromXML(s, UnifiedResponse.class);
            if (response != null) {
                response.setSign(st);
            }
            return response;
        } catch (ParserConfigurationException e) {
            throw new WechatSignException("微信返回值签名失败");
        } catch (IOException e) {
            throw new WechatSignException("微信返回值签名失败");
        } catch (SAXException e) {
            throw new WechatSignException("微信返回值签名失败");
        } catch (Exception e) {
            throw new WechatSignException("未知错误");
        }

    }
}
