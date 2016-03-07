package pengyi.application.permission.representation;

/**
 * Created by YJH on 2016/3/7.
 */
public class PermissionRepresentation {

    private String id;
    private Integer version;
    private String permissionName;                  //权限名
    private String description;                     //描述
    private Boolean status;                         //是否启用（true=启用，false=禁用）

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
}
