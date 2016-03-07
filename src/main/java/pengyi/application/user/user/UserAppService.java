package pengyi.application.user.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pengyi.domain.service.user.user.IUserService;

/**
 * Created by YJH on 2016/3/7.
 */
@Service("userAppService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class UserAppService implements IUserAppService {

    @Autowired
    private IUserService userService;

}
