package pengyi.application.role.command;

import pengyi.domain.model.permission.Permission;

import java.util.List;

/**
 * Created by YJH on 2016/3/7.
 */
public class EditRoleCommand {

    private String id;
    private Integer version;

    private String roleName;                        //角色名
    private String description;                     //描述
    private String[] permissions;            //权限列表

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

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String[] getPermissions() {
        return permissions;
    }

    public void setPermissions(String[] permissions) {
        this.permissions = permissions;
    }
}
