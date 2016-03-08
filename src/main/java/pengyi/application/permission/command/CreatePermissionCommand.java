package pengyi.application.permission.command;

import pengyi.core.type.EnableStatus;

/**
 * Created by YJH on 2016/3/7.
 */
public class CreatePermissionCommand {

    private String permissionName;                  //权限名
    private String description;                     //描述
    private EnableStatus status;                         //是否启用

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public EnableStatus getStatus() {
        return status;
    }

    public void setStatus(EnableStatus status) {
        this.status = status;
    }
}
