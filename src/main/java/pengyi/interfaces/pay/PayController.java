package pengyi.interfaces.pay;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pengyi.application.order.IOrderAppService;
import pengyi.domain.model.pay.AlipayNotify;
import pengyi.interfaces.shared.web.BaseController;

/**
 * Created by YJH on 2016/3/14.
 */
@Controller
@RequestMapping("/pay")
public class PayController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IOrderAppService orderAppService;

    @RequestMapping(value = "/alipay/notify")
    @ResponseBody
    public String alipayNotify(AlipayNotify notify) {

        return "success";
    }

    @RequestMapping(value = "/wechat/notify")
    @ResponseBody
    public String wechatNotify() {


        return "success";
    }

}
