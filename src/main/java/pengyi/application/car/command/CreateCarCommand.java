package pengyi.application.car.command;

import pengyi.domain.model.user.driver.Driver;

/**
 * Created by lvdi on 2016/3/8.
 */
public class CreateCarCommand {

    private String name;                //车辆名称
    private String carNumber;           //车牌号
    private String driver;              //司机

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

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }
}
