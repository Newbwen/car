package pengyi.interfaces;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Author: pengyi
 * Date: 15-12-30
 */
@Controller
@RequestMapping("/")
public class IndexController {

    @RequestMapping(value = "/")
    public ModelAndView index(){
        return new ModelAndView("index");
    }

}
