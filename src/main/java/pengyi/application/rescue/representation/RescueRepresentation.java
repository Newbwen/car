package pengyi.application.rescue.representation;

import pengyi.application.user.driver.representation.DriverRepresentation;
import pengyi.application.user.representation.BaseUserRepresentation;

/**
 * Created by LvDi on 2016/3/9.
 */
public class RescueRepresentation {
    private String id;
    private Integer version;

    private BaseUserRepresentation applyUser;                 //申请人
    private String applyTime;                   //申请时间
    private int type;                           //救援类型
    private String description;                 //救援说明
    private DriverRepresentation driver;                      //救援司机
    private String rescueTime;                  //救援时间
    private int status;                         //救援状态（1待救援、2救援中、3已救援）
    private String finishTime;                  //救援完成时间

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

    public BaseUserRepresentation getApplyUser() {
        return applyUser;
    }

    public void setApplyUser(BaseUserRepresentation applyUser) {
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

    public DriverRepresentation getDriver() {
        return driver;
    }

    public void setDriver(DriverRepresentation driver) {
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
}