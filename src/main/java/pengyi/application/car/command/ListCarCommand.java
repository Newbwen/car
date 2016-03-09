package pengyi.application.car.command;

import pengyi.core.commons.command.BasicPaginationCommand;
import pengyi.domain.model.user.driver.Driver;

/**
 * Created by lvdi on 2016/3/7.
 */
public class ListCarCommand extends BasicPaginationCommand {
    private String name;                //车辆名称
    private String carNumber;           //车牌号
    private Driver driver;              //司机

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }
}