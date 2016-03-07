package pengyi.domain.service.car;

import org.gjt.mm.mysql.Driver;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import pengyi.domain.model.car.Car;
import pengyi.domain.model.car.ICarRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created lvdi on 2016/3/7.
 */
public class CarService implements ICarService{
    @Autowired
    private ICarRepository carRepository;


/*
* 保存
* */
    @Override
    @SuppressWarnings("unchecked")
    public void save(Car car) {
        Car car1=new Car();
        car1.setName(car.getName());
        car1.setDriver(car.getDriver());
        car1.setCarNumber(car.getCarNumber());
        carRepository.save(car1);
    }
/*
* 根据Driver删除Car
* */
    @Override
    @SuppressWarnings("unchecked")
    public void delete(Driver driver) {

        Car car = getCarList(driver).get(0);
        carRepository.delete(car);


    }


    /*
    * 更新
    * */
    @Override
    @SuppressWarnings("unchecked")
    public void upadte(Car car) {
        Car car1=new Car();
        car1.setName(car.getName());
        car1.setDriver(car.getDriver());
        car1.setCarNumber(car.getCarNumber());
        carRepository.update(car1);
    }

/*
* 根据条件查询
* */
    @Override
    @SuppressWarnings("unchecked")
    public List<Car> getCarList(Driver driver) {
        List<Criterion> criterionList = new ArrayList<Criterion>();
        criterionList.add(Restrictions.eq("driver", driver));

        return carRepository.list((Criterion[])criterionList.toArray(), null);
    }
}
