package pengyi.repository.user.driver;

import org.springframework.stereotype.Repository;
import pengyi.domain.model.user.driver.Driver;
import pengyi.domain.model.user.driver.IDriverRepository;
import pengyi.repository.generic.AbstractHibernateGenericRepository;

/**
 * Created by YJH on 2016/3/7.
 */
@Repository("driverRepository")
public class DriverRepository extends AbstractHibernateGenericRepository<Driver, String>
        implements IDriverRepository<Driver, String> {
}
