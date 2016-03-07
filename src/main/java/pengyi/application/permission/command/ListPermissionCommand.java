package pengyi.application.permission.command;

import pengyi.core.commons.command.BasicPaginationCommand;

/**
 * Created by YJH on 2016/3/7.
 */
public class ListPermissionCommand extends BasicPaginationCommand {
    private String permissionName;
    private Boolean status;

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
