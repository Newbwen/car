package pengyi.application.report.command;


/**
 * Created by liubowen on 2016/3/10.
 * 页面修改
 */
public class EditReportCommand {
    private String id;
    private Integer version;
    private String status;                  //状态（待处理、处理中、处理完成）

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


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
