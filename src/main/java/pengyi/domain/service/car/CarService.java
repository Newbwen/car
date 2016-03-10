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

      Car car=this.show(command.getId());
        car.fainWhenConcurrencyViolation(command.getVersion());
        if(!car.getCarNumber().equals(command.getCarNumber())){
            throw new ExistException("车牌号为[" + command.getCarNumber() + "]的记录已存在");

        }
        Driver driver=driverService.show(command.getDriver());
        Car car1=new Car(command.getName(),command.getCarNumber(),driver);
        carRepository.save(car1);
        return car1;
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
        return carRepository.getBynuBNumber(carNumber);
    }


    @Override
    public Car create(CreateCarCommand command) {

        if(null!=command.getCarNumber()){
            throw new ExistException("车辆[" + command.getCarNumber() + "]的记录已存在");
        }

        Driver driver=driverService.show(command.getDriver());
        Car car=new Car(command.getName(),command.getCarNumber(),driver);
        carRepository.save(car);
        return car;
    }

    @Override
    public Car updateCar(EditCarCommand command) {
        Car car=this.show(command.getId());
        car.fainWhenConcurrencyViolation(command.getVersion());
        if(car.getCarNumber()==null){
            car.setCarNumber(command.getCarNumber());
        }
        carRepository.update(car);
        return car;
    }
}
