package pengyi.application.message.command;

/**
 * Created by liubowen on 2016/3/9.
 */
public class EditMessageCommand {

    private String id;
    private Integer version;

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
}
