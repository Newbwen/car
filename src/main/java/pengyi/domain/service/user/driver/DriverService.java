package pengyi.domain.service.user.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pengyi.domain.model.user.driver.Driver;
import pengyi.domain.model.user.driver.IDriverRepository;

/**
 * Created by YJH on 2016/3/7.
 */
@Service("driverService")
public class DriverService implements IDriverService {

    @Autowired
    private IDriverRepository<Driver, String> driverRepository;
}
