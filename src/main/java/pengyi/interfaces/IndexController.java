package pengyi.interfaces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import pengyi.domain.service.user.IBaseUserService;

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
