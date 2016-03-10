package pengyi.application.rescue.command;

import pengyi.core.commons.command.BasicPaginationCommand;
import pengyi.domain.model.user.BaseUser;
import pengyi.domain.model.user.driver.Driver;

/**
 * Created by YJH on 2016/3/9.
 */
public class ListRescueCommand extends BasicPaginationCommand {
    private String applyUser;                 //申请人
    private String driver;                      //救援司机

    public String getApplyUser() {
        return applyUser;
    }

    public void setApplyUser(String applyUser) {
        this.applyUser = applyUser;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }
}
