package pengyi.interfaces.pay;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pengyi.application.order.IOrderAppService;
import pengyi.application.order.representation.OrderRepresentation;
import pengyi.core.type.PayType;
import pengyi.core.util.Signature;
import pengyi.domain.model.pay.AlipayNotify;
import pengyi.domain.service.pay.IPayService;
import pengyi.interfaces.shared.web.BaseController;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;
import java.util.Map;

/**
 * Created by YJH on 2016/3/14.
 */
@Controller
@RequestMapping("/pay")
public class PayController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IPayService payService;

    @RequestMapping(value = "/alipay/notify")
    @ResponseBody
    public String alipayNotify(AlipayNotify notify, HttpServletRequest request, Locale locale) throws IllegalAccessException {

        Map<String, Object> map = request.getParameterMap();
        for (Map.Entry entry : map.entrySet()) {
            logger.warn(entry.getKey() + ">>>>>>>>>>>>>>>>>>>>>>" + entry.getValue());
        }
        String sign = notify.getSign();
        notify.setSign("");
        notify.setSign_type("");
        String mySign = Signature.getSign(notify);

        https://mapi.alipay.com/gateway.do?service=notify_verify&partner=2088002396712354&notify_id=RqPnCoPT3K9%252Fvwbh3I%252BFioE227%252BPfNMl8jwyZqMIiXQWxhOCmQ5MQO%252FWd93rvCB%252BaiGg

        if (mySign.equals(sign)) {
            if (notify.getTrade_status().equals("TRADE_SUCCESS")) {
                logger.info(getMessage("pay.success.message", new Object[]{notify.getOut_trade_no(), PayType.ALIPAY}, locale));
                payService.alipaySuccess(notify);
            } else if (notify.getTrade_status().equals("TRADE_FINISHED")) {
                logger.info(getMessage("pay.success.message", new Object[]{notify.getOut_trade_no(), PayType.ALIPAY}, locale));
                payService.alipaySuccess(notify);
            } else if (notify.getTrade_status().equals("WAIT_BUYER_PAY")) {

            }
        }


        return "success";
    }

    @RequestMapping(value = "/wechat/notify")
    @ResponseBody
    public String wechatNotify() {


        return "success";
    }

}
