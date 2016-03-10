package pengyi.repository.car;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import pengyi.domain.model.car.Car;
import pengyi.domain.model.car.ICarRepository;
import pengyi.domain.model.rescue.Rescue;
import pengyi.repository.generic.AbstractHibernateGenericRepository;

/**
 * Author: lvdi
 * Date: 15-3-8
 */
@Repository("carRepository")
public class CarRepository extends AbstractHibernateGenericRepository<Car, String> implements ICarRepository<Car, String> {

    @Override
    public Car getBynuBNumber(String carNumber) {

        Criteria criteria=getSession().createCriteria(getPersistentClass());
        criteria.add(Restrictions.eq("carNumber",carNumber));
        return (Car) criteria.uniqueResult();
    }
}