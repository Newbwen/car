package pengyi.interfaces.app.api.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pengyi.application.order.IApiOrderAppService;
import pengyi.application.order.command.CreateOrderCommand;
import pengyi.core.api.BaseResponse;
import pengyi.core.api.ResponseCode;
import pengyi.core.commons.Constants;
import pengyi.domain.model.user.BaseUser;

import javax.servlet.http.HttpSession;

/**
 * Created by YJH on 2016/3/22.
 */
@Controller
@RequestMapping("/api/app/order")
public class ApiAppOrderController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IApiOrderAppService apiOrderAppService;

    @RequestMapping(value = "/create_order")
    @ResponseBody
    public BaseResponse createOrder(CreateOrderCommand command, HttpSession session) {
        long startTime = System.currentTimeMillis();
        BaseUser baseUser = (BaseUser) session.getAttribute(Constants.SESSION_USER);
        if (null == baseUser) {
            return new BaseResponse(ResponseCode.RESPONSE_CODE_NOT_LOGIN,
                    System.currentTimeMillis() - startTime, null, ResponseCode.RESPONSE_CODE_NOT_LOGIN.getMessage());
        }
        command.setOrderUser(baseUser.getId());
        BaseResponse response = null;
        try {
            response = apiOrderAppService.createOrder(command);
        } catch (Exception e) {

        }
        response.setDebug_time(System.currentTimeMillis() - startTime);
        return response;
    }

}
