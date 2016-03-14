package pengyi.application.message.command;

/**
 * Created by liubowen on 2016/3/14.
 */
public class DeleteMessageCommand {
    private String id;
    private String version;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
