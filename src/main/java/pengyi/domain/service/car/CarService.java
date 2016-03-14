package pengyi.domain.service.car;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pengyi.application.car.command.CreateCarCommand;
import pengyi.application.car.command.EditCarCommand;
import pengyi.application.car.command.ListCarCommand;
import pengyi.core.exception.ExistException;
import pengyi.core.exception.NoFoundException;
import pengyi.core.util.CoreStringUtils;
import pengyi.domain.model.car.Car;
import pengyi.domain.model.car.ICarRepository;
import pengyi.domain.model.user.driver.Driver;
import pengyi.domain.service.user.driver.IDriverService;
import pengyi.repository.generic.Pagination;

import java.util.ArrayList;
import java.util.List;

/**
 * Created lvdi on 2016/3/8.
 */
@Service("carService")
public class CarService implements ICarService{
    @Autowired
    private ICarRepository carRepository;
    @Autowired
    private IDriverService driverService;


    @Override
    public Pagination<Car> pagination(ListCarCommand command) {
        List<Criterion> criteriaList = new ArrayList();
        if (!CoreStringUtils.isEmpty(command.getCarNumber())) {
            criteriaList.add(Restrictions.eq("carNumber", command.getCarNumber()));
        }
        if (!CoreStringUtils.isEmpty(command.getDriver())) {
            criteriaList.add(Restrictions.eq("driverId", command.getDriver()));
        }
        if(!CoreStringUtils.isEmpty(command.getName())){
            criteriaList.add(Restrictions.like("carName",command.getName()));
        }
        return carRepository.pagination(command.getPage(),command.getPageSize(),criteriaList,null);
    }




    @Override
    public Car edit(EditCarCommand command) {
        List<Criterion> criteriaList = new ArrayList();

        criteriaList.add(Restrictions.eq("carNumber", command.getCarNumber()));


      Car car=this.searchByNumber(command.getCarNumber());
        car.fainWhenConcurrencyViolation(command.getVersion());
        if(!car.getCarNumber().equals(command.getCarNumber())){
            throw new ExistException("车牌号为[" + command.getCarNumber() + "]的记录已存在");

        }
//       Driver driver=driverService.show(command.getDriver();
//        car.setDriver(driver);
        car.setCarNumber(command.getCarNumber());
        car.setName(command.getName());
        carRepository.update(car);
        return car;
    }

    @Override
        public Car show(String id) {
        Car car= (Car) carRepository.getById(id);
        if (null == car) {
            throw new NoFoundException("没有找到车辆id=[" + id + "]的记录");
        }
        return car;
    }

    @Override
    public Car searchByNumber(String carNumber) {
        return carRepository.getBynuNumber(carNumber);
    }

    @Override
    public Car searchByDriver(String driver) {
        return null;
    }


    @Override
    public Car create(CreateCarCommand command) {

        Car car = this.searchByNumber(command.getCarNumber());
        if(null!=car){

            throw new ExistException("车辆[" + command.getCarNumber() + "]的记录已存在");

        }

        Driver driver=driverService.show(command.getDriver());
        Car car2=new Car(command.getName(),command.getCarNumber(),driver);
        carRepository.save(car2);
        return car2;
    }


}
