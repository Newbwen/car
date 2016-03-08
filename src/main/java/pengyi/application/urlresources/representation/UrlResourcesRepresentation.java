package pengyi.application.urlresources.representation;

import pengyi.application.permission.representation.PermissionRepresentation;

import java.util.List;

/**
 * Created by YJH on 2016/3/7.
 */
public class UrlResourcesRepresentation {

    private String id;
    private Integer version;

    private String urlName;                     //路径名
    private String description;                 //描述
    private List<PermissionRepresentation> urlPermission;     //路径权限列表
    private Boolean status;                     //是否启用（true=启用，false=禁用）

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

    public String getUrlName() {
        return urlName;
    }

    public void setUrlName(String urlName) {
        this.urlName = urlName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<PermissionRepresentation> getUrlPermission() {
        return urlPermission;
    }

    public void setUrlPermission(List<PermissionRepresentation> urlPermission) {
        this.urlPermission = urlPermission;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
