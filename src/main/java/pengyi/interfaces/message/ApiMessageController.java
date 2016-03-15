package pengyi.interfaces.message;

        import org.slf4j.Logger;
        import org.slf4j.LoggerFactory;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Controller;
        import org.springframework.web.bind.annotation.RequestMapping;
        import pengyi.application.message.IApiMessageService;

/**
 * Created by liubowen on 2016/3/15.
 */
@Controller
@RequestMapping("/message_show/api")
public class ApiMessageController {
    private Logger logger= LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IApiMessageService apiMessageService;

}
