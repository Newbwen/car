package pengyi.application.user.driver.command;

import pengyi.application.user.command.BaseEditBaseUserCommand;
import pengyi.core.type.DriverType;
import pengyi.core.type.Sex;

/**
 * Created by YJH on 2016/3/15.
 */
public class CompanyDriverEditCommand extends BaseEditBaseUserCommand {

    private DriverType driverType;

    public DriverType getDriverType() {
        return driverType;
    }

    public void setDriverType(DriverType driverType) {
        this.driverType = driverType;
    }
}
