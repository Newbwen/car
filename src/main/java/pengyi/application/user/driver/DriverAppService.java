package pengyi.application.user.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pengyi.domain.service.user.driver.IDriverService;

/**
 * Created by YJH on 2016/3/7.
 */
@Service("driverAppService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class DriverAppService implements IDriverAppService {

    @Autowired
    private IDriverService driverService;

}
