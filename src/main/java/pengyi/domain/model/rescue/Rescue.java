package pengyi.domain.model.rescue;

import pengyi.core.type.RescueStatus;
import pengyi.domain.model.base.Identity;
import pengyi.domain.model.user.BaseUser;
import pengyi.domain.model.user.driver.Driver;

import java.util.Date;

/**
 * 救援
 * Created by pengyi on 2016/3/4.
 */
public class Rescue extends Identity {

    private BaseUser applyUser;                 //申请人
    private Date applyTime;                   //申请时间
    private int type;                           //救援类型
    private String description;                 //救援说明
    private Driver driver;                      //救援司机
    private Date rescueTime;                  //救援时间
    private RescueStatus status;                         //救援状态（1待救援、2救援中、3已救援）
    private Date finishTime;                  //救援完成时间

    public BaseUser getApplyUser() {
        return applyUser;
    }

    public void setApplyUser(BaseUser applyUser) {
        this.applyUser = applyUser;
    }

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
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

    public Date getRescueTime() {
        return rescueTime;
    }

    public void setRescueTime(Date rescueTime) {
        this.rescueTime = rescueTime;
    }

    public RescueStatus getStatus() {
        return status;
    }

    public void setStatus(RescueStatus status) {
        this.status = status;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    public Rescue() {
    }

    public Rescue(BaseUser applyUser, Date applyTime, int type, String description, Driver driver, Date rescueTime, RescueStatus status, Date finishTime) {
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
