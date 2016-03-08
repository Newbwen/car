package pengyi.repository.car;

import org.gjt.mm.mysql.Driver;
import org.springframework.stereotype.Repository;
import pengyi.domain.model.car.Car;
import pengyi.domain.model.car.ICarRepository;
import pengyi.domain.model.user.BaseUser;
import pengyi.domain.model.user.IUserRepository;
import pengyi.repository.generic.AbstractHibernateGenericRepository;

/**
 * Author: pengyi
 * Date: 15-12-30
 */
@Repository("userRepository")
public class CarResposition extends AbstractHibernateGenericRepository<Car, String> implements ICarRepository<Car, String> {
}