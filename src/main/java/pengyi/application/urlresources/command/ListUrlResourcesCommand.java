package pengyi.application.urlresources.command;

import pengyi.core.commons.command.BasicPaginationCommand;

/**
 * Created by YJH on 2016/3/7.
 */
public class ListUrlResourcesCommand extends BasicPaginationCommand {

    private String urlName;     //路径名
    private Boolean status;     //状态

    public String getUrlName() {
        return urlName;
    }

    public void setUrlName(String urlName) {
        this.urlName = urlName;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
