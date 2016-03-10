package pengyi.application.rescue.command;

import pengyi.core.type.RescueStatus;
import pengyi.domain.model.user.BaseUser;
import pengyi.domain.model.user.driver.Driver;

/**
 * Created by YJH on 2016/3/9.
 */
public class EditRescueCommand {
    private String id;
    private Integer version;

    private String applyUser;                 //申请人
    private int type;                           //救援类型
    private String description;                 //救援说明
    private String driver;                      //救援司机
    private RescueStatus status;                         //救援状态（1待救援、2救援中、3已救援）
    private String rescueTime;                  //救援时间

    public RescueStatus getStatus() {
        return status;
    }

    public void setStatus(RescueStatus status) {
        this.status = status;
    }

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

    public String getApplyUser() {
        return applyUser;
    }

    public void setApplyUser(String applyUser) {
        this.applyUser = applyUser;
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

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getRescueTime() {
        return rescueTime;
    }

    public void setRescueTime(String rescueTime) {
        this.rescueTime = rescueTime;
    }
}
