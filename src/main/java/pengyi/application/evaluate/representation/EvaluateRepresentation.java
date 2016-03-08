package pengyi.application.evaluate.representation;

import pengyi.application.user.representation.BaseUserRepresentation;
import pengyi.domain.model.order.Order;
import pengyi.domain.model.user.BaseUser;
import pengyi.repository.order.OrderResposition;
import pengyi.repository.user.BaseUserRepository;

/**
 * Created by ${lvdi} on 2016/3/8.
 */
public class EvaluateRepresentation {

    private Integer version;

    private BaseUserRepresentation evaluateUser;               //评价人
    private OrderResposition order;                            //订单
    private String content;                                    //评价内容
    private int level;                                         //评级
    private String createDate;                                 //评价时间

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public BaseUserRepresentation getEvaluateUser() {
        return evaluateUser;
    }

    public void setEvaluateUser(BaseUserRepresentation evaluateUser) {
        this.evaluateUser = evaluateUser;
    }

    public OrderResposition getOrder() {
        return order;
    }

    public void setOrder(OrderResposition order) {
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
