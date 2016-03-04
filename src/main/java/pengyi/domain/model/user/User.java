package pengyi.domain.model.user;

import pengyi.domain.model.base.Identity;

import java.math.BigDecimal;

/**
 * 用户
 * Created by pengyi on 2016/3/4.
 */
public class User extends Identity {

    private BaseUser baseUser;              //用户
    private String name;                    //用户名
    private String head;                    //头像
    private int sex;                        //性别（0为男，2为女）
    private int integral;                   //积分
    private BigDecimal money;               //余额
    private int reportCount;                //举报次数

    public BaseUser getBaseUser() {
        return baseUser;
    }

    public void setBaseUser(BaseUser baseUser) {
        this.baseUser = baseUser;
    }

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

    public User() {
        super();
    }

    public User(BaseUser baseUser, String name, String head, int sex, int integral, BigDecimal money, int reportCount) {
        super();
        this.baseUser = baseUser;
        this.name = name;
        this.head = head;
        this.sex = sex;
        this.integral = integral;
        this.money = money;
        this.reportCount = reportCount;
    }
}
