package pengyi.interfaces.pay;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pengyi.application.pay.IPayAppService;
import pengyi.core.commons.Constants;
import pengyi.core.type.PayType;
import pengyi.core.util.HttpUtil;
import pengyi.core.util.SignUtils;
import pengyi.core.util.Signature;
import pengyi.domain.model.pay.AlipayNotify;
import pengyi.domain.model.pay.WechatNotify;
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
    private IPayAppService payAppService;

    @RequestMapping(value = "/alipay/notify")
    @ResponseBody
    public String alipayNotify(AlipayNotify notify, HttpServletRequest request, Locale locale) {

        Map<String, Object> map = request.getParameterMap();
        for (Map.Entry entry : map.entrySet()) {
            logger.info(entry.getKey() + ">----------->" + ((String[])entry.getValue())[0]);
        }
        try {
            if ("true".equals(HttpUtil.urlConnection(Constants.ALIPAY_NOTIFY_VERIFY_URL,
                    Constants.ALIPAY_NOTIFY_VERIFY_PARAM + notify.getNotify_id()))) {
                if (notify.getTrade_status().equals("TRADE_SUCCESS")) {
                    payAppService.alipaySuccess(notify);
                    logger.info(getMessage("pay.success.message", new Object[]{notify.getOut_trade_no(), PayType.ALIPAY}, locale));
                } else if (notify.getTrade_status().equals("TRADE_FINISHED")) {
                    payAppService.alipaySuccess(notify);
                    logger.info(getMessage("pay.success.message", new Object[]{notify.getOut_trade_no(), PayType.ALIPAY}, locale));
                } else if (notify.getTrade_status().equals("WAIT_BUYER_PAY")) {

                }
                return "true";
            } else {
                logger.info(getMessage("pay.fail.message", new Object[]{notify.getOut_trade_no(), "不是支付宝通知"}, locale));
            }
        } catch (Exception e) {
            logger.info(getMessage("pay.fail.message", new Object[]{notify.getOut_trade_no(), e.getMessage()}, locale));
        }

        return "false";
    }

    @RequestMapping(value = "/wechat/notify")
    @ResponseBody
    public String wechatNotify(WechatNotify notify, HttpServletRequest request, Locale locale) {

        Map<String, Object> map = request.getParameterMap();
        for (Map.Entry entry : map.entrySet()) {
            logger.warn(entry.getKey() + ">>>>>>>>>>>>>>>>>>>>>>" + entry.getValue().toString());
        }
        String sign = notify.getSign();
        notify.setSign(sign);
        try {
            String mySign = Signature.getWechatSign(notify);
            if (mySign.equals(sign)) {
                if (notify.getReturn_code().equals("SUCCESS")) {
                    if (notify.getResult_code().equals("SUCCESS")) {
                        payAppService.wechatSuccess(notify);
                        logger.info(getMessage("pay.success.message", new Object[]{notify.getOut_trade_no(), PayType.WECHAT}, locale));
                    } else {
                        logger.info(getMessage("pay.fail.message", new Object[]{notify.getOut_trade_no(), notify.getErr_code_des()}, locale));
                    }

                } else {
                    logger.info(getMessage("pay.fail.message", new Object[]{notify.getOut_trade_no(), notify.getReturn_msg()}, locale));
                }
            } else {
                logger.info(getMessage("pay.fail.message", new Object[]{notify.getOut_trade_no(), "签名验证失败"}, locale));
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return "success";
    }

}
