package pengyi.domain.model.permission;

import pengyi.domain.model.base.Identity;

/**
 * 权限
 * Created by pengyi on 2016/3/4.
 */
public class Permission extends Identity{

    private String permissionName;                  //权限名
    private String description;                     //描述
    private Boolean status;                         //是否启用

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

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Permission() {
        super();
    }

    public Permission(String permissionName, String description, Boolean status) {
        super();
        this.permissionName = permissionName;
        this.description = description;
        this.status = status;
    }
}
