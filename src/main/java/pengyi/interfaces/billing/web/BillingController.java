package pengyi.interfaces.billing.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import pengyi.application.billing.IBillingAppService;
import pengyi.interfaces.shared.web.BaseController;

/**
 * Created by YJH on 2016/3/21.
 */
@Controller
@RequestMapping("billing")
public class BillingController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IBillingAppService billingAppService;

}
