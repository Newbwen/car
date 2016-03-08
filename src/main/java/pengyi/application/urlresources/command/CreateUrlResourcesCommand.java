package pengyi.application.urlresources.command;

/**
 * Created by YJH on 2016/3/7.
 */
public class CreateUrlResourcesCommand {

    private String urlName;                     //路径名
    private String description;                 //描述
    private String[] urlPermission;     //路径权限列表
    private Boolean status;                     //是否启用（true=启用，false=禁用）

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

    public String[] getUrlPermission() {
        return urlPermission;
    }

    public void setUrlPermission(String[] urlPermission) {
        this.urlPermission = urlPermission;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
