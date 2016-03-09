package pengyi.application.evaluate.command;


/**
 * Created by ${lvdi} on 2016/3/8.
 */
public class EditEvaluateCommand {
    private String id;
    private Integer version;

    private String evaluateUser;                     //评价人
    private String order;                            //订单
    private String content;                          //评价内容
    private Integer level;                              //评级

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

    public String getEvaluateUser() {
        return evaluateUser;
    }

    public void setEvaluateUser(String evaluateUser) {
        this.evaluateUser = evaluateUser;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}
