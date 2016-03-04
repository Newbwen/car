package pengyi.domain.model.rescue;

import pengyi.domain.model.base.Identity;
import pengyi.domain.model.user.BaseUser;
import pengyi.domain.model.user.Driver;

/**
 * 救援
 * Created by pengyi on 2016/3/4.
 */
public class Rescue extends Identity {

    private BaseUser applyUser;                 //申请人
    private String applyTime;                   //申请时间
    private int type;                           //救援类型
    private String description;                 //救援说明
    private Driver driver;                      //救援司机
    private String rescueTime;                  //救援时间
    private int status;                         //救援状态（1待救援、2救援中、3已救援）
    private String finishTime;                  //救援完成时间

    public BaseUser getApplyUser() {
        return applyUser;
    }

    public void setApplyUser(BaseUser applyUser) {
        this.applyUser = applyUser;
    }

    public String getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(String applyTime) {
        this.applyTime = applyTime;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public String getRescueTime() {
        return rescueTime;
    }

    public void setRescueTime(String rescueTime) {
        this.rescueTime = rescueTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
    }

    public Rescue() {
        super();
    }

    public Rescue(BaseUser applyUser, String applyTime, int type, String description, Driver driver, String rescueTime, int status, String finishTime) {
        super();
        this.applyUser = applyUser;
        this.applyTime = applyTime;
        this.type = type;
        this.description = description;
        this.driver = driver;
        this.rescueTime = rescueTime;
        this.status = status;
        this.finishTime = finishTime;
    }
}
