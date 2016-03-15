package pengyi.interfaces.permission.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import pengyi.application.role.IApiRoleAppService;

/**
 * Created by YJH on 2016/3/15.
 */
@Controller
@RequestMapping("/role/api")
public class ApiPermissionController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IApiRoleAppService apiRoleAppService;

}