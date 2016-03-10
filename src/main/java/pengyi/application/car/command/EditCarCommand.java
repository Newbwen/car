package pengyi.application.car.command;

/**
 * Created by lvdi on 2016/3/8.
 */
public class EditCarCommand {

    private String id;
    private Integer version;

    private String name;                //车辆名称
    private String carNumber;           //车牌号

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

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


}
