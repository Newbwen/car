package pengyi.repository.car;

import org.springframework.stereotype.Repository;
import pengyi.domain.model.car.Car;
import pengyi.domain.model.car.ICarRepository;
import pengyi.repository.generic.AbstractHibernateGenericRepository;

/**
 * Author: pengyi
 * Date: 15-12-30
 */
@Repository("carRepository")
public class CarResposition extends AbstractHibernateGenericRepository<Car, String> implements ICarRepository<Car, String> {
}