package pengyi.application.user.user.representation;

import pengyi.application.user.representation.BaseUserRepresentation;

import java.math.BigDecimal;

/**
 * Created by YJH on 2016/3/7.
 */
public class UserRepresentation extends BaseUserRepresentation {

    private String name;                    //用户名
    private String head;                    //头像
    private int sex;                        //性别（0为男，2为女）
    private int integral;                   //积分
    private BigDecimal money;               //余额
    private int reportCount;                //举报次数

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getIntegral() {
        return integral;
    }

    public void setIntegral(int integral) {
        this.integral = integral;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public int getReportCount() {
        return reportCount;
    }

    public void setReportCount(int reportCount) {
        this.reportCount = reportCount;
    }
}
