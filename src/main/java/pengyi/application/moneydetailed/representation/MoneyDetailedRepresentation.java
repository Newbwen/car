package pengyi.application.moneydetailed.representation;

import pengyi.application.user.representation.BaseUserRepresentation;
import pengyi.core.type.FlowType;

import java.math.BigDecimal;

/**
 * Created by YJH on 2016/3/9.
 */
public class MoneyDetailedRepresentation {

    private String id;
    private Integer version;

    private BaseUserRepresentation baseUser;      //用户
    private FlowType flowType;  //资金流向类型
    private BigDecimal money;   //金额
    private String explain;     //说明（如：专车订单+订单号）
    private BigDecimal oldMoney;    //原有金额
    private BigDecimal newMoney;    //现有金额

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

    public BaseUserRepresentation getBaseUser() {
        return baseUser;
    }

    public void setBaseUser(BaseUserRepresentation baseUser) {
        this.baseUser = baseUser;
    }

    public FlowType getFlowType() {
        return flowType;
    }

    public void setFlowType(FlowType flowType) {
        this.flowType = flowType;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }

    public BigDecimal getOldMoney() {
        return oldMoney;
    }

    public void setOldMoney(BigDecimal oldMoney) {
        this.oldMoney = oldMoney;
    }

    public BigDecimal getNewMoney() {
        return newMoney;
    }

    public void setNewMoney(BigDecimal newMoney) {
        this.newMoney = newMoney;
    }
}
