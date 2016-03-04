package pengyi.interfaces.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pengyi.domain.model.user.BaseUser;
import pengyi.domain.service.user.IUserService;

import java.util.List;

/**
 * Author: pengyi
 * Date: 15-12-30
 */
@Controller
@RequestMapping("/")
public class IndexController {

    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/")
    public ModelAndView index() throws Exception {
        List<BaseUser> baseUserList = userService.getUserList();


        return new ModelAndView("/send", "users", baseUserList);
    }

    @RequestMapping(value = "/api")
    public ModelAndView api() throws Exception {
        return new ModelAndView("/api");
    }
}
