package pengyi.application.evaluate.command;


import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by ${lvdi} on 2016/3/8.
 */
public class CreateEvaluateCommand {

    @NotEmpty(message = "{evaluate.evaluateUser,NotEmpty,message}")
    private String evaluateUser;                      //评价人

    @NotEmpty(message = "{evaluate.order,NotEmpty,message}")
    private String order;                             //订单

    @NotEmpty(message = "{evaluate.content,NotEmpty,message}")
    private String content;                           //评价内容

    @NotEmpty(message = "{evaluate.level,NotEmpty,message}")
    private int level;                                //评级

    @NotEmpty(message = "{evaluate.createDate,NotEmpty,message}")
    private String createDate;                       //评价时间


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

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
}
