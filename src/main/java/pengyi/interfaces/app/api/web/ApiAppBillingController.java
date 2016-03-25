package pengyi.interfaces.app.api.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pengyi.application.billing.IApiBillingAppService;
import pengyi.application.user.driver.representation.DriverRepresentation;
import pengyi.core.api.BaseResponse;
import pengyi.core.api.ResponseCode;
import pengyi.core.commons.Constants;
import pengyi.core.type.UserType;
import pengyi.domain.model.user.BaseUser;

import javax.servlet.http.HttpSession;

/**
 * Created by YJH on 2016/3/22.
 */
@Controller
@RequestMapping("/api/app/billing")
public class ApiAppBillingController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IApiBillingAppService apiBillingAppService;

}
