package pengyi.application.user.driver.command;

import pengyi.application.user.command.BaseEditBaseUserCommand;
import pengyi.core.type.DriverType;
import pengyi.core.type.Sex;

/**
 * Created by YJH on 2016/3/7.
 */
public class EditDriverCommand extends BaseEditBaseUserCommand {

    private String name;                    //姓名
    private Sex sex;                        //性别（0为男，1为女）
    private DriverType driverType;                       //类型

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public DriverType getDriverType() {
        return driverType;
    }

    public void setDriverType(DriverType driverType) {
        this.driverType = driverType;
    }
}
