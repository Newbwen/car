package pengyi.interfaces.web.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pengyi.core.response.BaseResponse;
import pengyi.domain.service.user.IUserService;

/**
 * Author: pengyi
 * Date: 15-12-30
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/create")
    @ResponseBody
    public BaseResponse create(String user_id) throws Exception {
        long starttime = System.currentTimeMillis();
        try {
            userService.save(user_id);
            return new BaseResponse(10000, System.currentTimeMillis() - starttime, null);
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResponse(90000, System.currentTimeMillis() - starttime, null);
        }
    }

}
