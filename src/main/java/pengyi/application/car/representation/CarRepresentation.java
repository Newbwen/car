package pengyi.application.car.representation;

import pengyi.application.user.driver.representation.DriverRepresentation;
/**
 * Created by lvdi on 2016/3/8.
 */
public class CarRepresentation {

    private String id;
    private Integer version;

    private String name;                //车辆名称
    private String carNumber;           //车牌号
    private DriverRepresentation driver;              //司机

    public String getId() { return id; }

    public Integer getVersion() { return version; }

    public void setId(String id) { this.id = id; }

    public void setVersion(Integer version) { this.version = version; }

    public String getName() { return name; }

    public void setName(String name) {
        this.name = name;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public DriverRepresentation getDriver() {
        return driver;
    }

    public void setDriver(DriverRepresentation driver) {
        this.driver = driver;
    }
}
