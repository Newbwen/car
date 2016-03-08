package pengyi.domain.service.car;

import org.gjt.mm.mysql.Driver;
import pengyi.domain.model.car.Car;

import java.util.List;

/**
 * Created by lvdi on 2015/3/7.
 */
public interface ICarService {

    void save(Car car);

    void delete(Driver driver);

    void upadte(Car car);
    List<Car> getCarList(Driver driver);

}
