package pengyi.application.rescue.command;

import org.hibernate.validator.constraints.NotEmpty;
import pengyi.core.type.RescueStatus;
import pengyi.core.type.RescueType;

import javax.validation.constraints.NotNull;

/**
 * Created by LvDi on 2016/3/9.
 */
public class CreateRescueCommand {

    @NotEmpty(message = "{rescue.applyUser,NotEmpty,message}")
    private String applyUser;                   //申请人

    @NotNull(message = "{rescue.type,Notnull,message}")
    private RescueType rescueType;                           //救援类型

    @NotEmpty(message = "{rescue.description,NotEmpty,message}")
    private String description;                 //救援说明

    public String getApplyUser() {
        return applyUser;
    }

    public void setApplyUser(String applyUser) {
        this.applyUser = applyUser;
    }

    public RescueType getRescueType() {
        return rescueType;
    }

    public void setRescueType(RescueType rescueType) {
        this.rescueType = rescueType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
