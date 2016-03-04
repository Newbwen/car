package pengyi.domain.model.evaluate;

import pengyi.domain.model.base.Identity;
import pengyi.domain.model.order.Order;
import pengyi.domain.model.user.BaseUser;

/**
 * 评价
 * Created by pengyi on 2016/3/4.
 */
public class Evaluate extends Identity{

    private BaseUser evaluateUser;                  //评价人
    private Order order;                            //订单
    private String content;                         //评价内容
    private int level;                              //评级

    public BaseUser getEvaluateUser() {
        return evaluateUser;
    }

    public void setEvaluateUser(BaseUser evaluateUser) {
        this.evaluateUser = evaluateUser;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
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

    public Evaluate() {
        super();
    }

    public Evaluate(BaseUser evaluateUser, Order order, String content, int level) {
        super();
        this.evaluateUser = evaluateUser;
        this.order = order;
        this.content = content;
        this.level = level;
    }
}
