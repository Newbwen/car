package pengyi.application.role.command;

/**
 * Created by YJH on 2016/3/7.
 */
public class CreateRoleCommand {

    private String roleName;                        //角色名
    private String description;                     //描述
    private Boolean status;                         //是否启用
    private String[] permissions;                   //权限列表

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

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String[] getPermissions() {
        return permissions;
    }

    public void setPermissions(String[] permissions) {
        this.permissions = permissions;
    }
}
