package pengyi.application.order.command;

import pengyi.core.type.OrderStatus;

/**
 * Created by YJH on 2016/3/8.
 */
public class UpDateOrderStatusCommand {

    private String id;
    private Integer version;

    private OrderStatus status;

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

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}
