package pengyi.interfaces.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pengyi.domain.model.user.BaseUser;
import pengyi.domain.service.user.IBaseUserService;

import java.util.List;

/**
 * Author: pengyi
 * Date: 15-12-30
 */
@Controller
@RequestMapping("/")
public class IndexController {

    @Autowired
    private IBaseUserService userService;

}
