package pengyi.repository.car;

import org.springframework.stereotype.Repository;
import pengyi.domain.model.car.Car;
import pengyi.domain.model.car.ICarRepository;
import pengyi.repository.generic.AbstractHibernateGenericRepository;

/**
 * Author: lvdi
 * Date: 15-3-8
 */
@Repository("carRepository")
public class CarRepository extends AbstractHibernateGenericRepository<Car, String> implements ICarRepository<Car, String> {
}