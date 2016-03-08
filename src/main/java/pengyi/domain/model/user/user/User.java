package pengyi.domain.model.user.user;

import pengyi.domain.model.area.Area;
import pengyi.domain.model.base.Identity;
import pengyi.domain.model.role.Role;
import pengyi.domain.model.user.BaseUser;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * update by yjh
 *
 * 用户
 * Created by pengyi on 2016/3/4.
 */
public class User extends BaseUser {

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

    public User() {
        super();
    }

    public User(String name, String head, int sex, int integral, BigDecimal money, int reportCount) {
        this.name = name;
        this.head = head;
        this.sex = sex;
        this.integral = integral;
        this.money = money;
        this.reportCount = reportCount;
    }

    public User(String phone, String password, String salt, Boolean status, BigDecimal balance, Date createDate, Role userRole, String email, int type, String name, String head, int sex, int integral, BigDecimal money, int reportCount) {
        super(phone, password, salt, status, balance, createDate, userRole, email, type);
        this.name = name;
        this.head = head;
        this.sex = sex;
        this.integral = integral;
        this.money = money;
        this.reportCount = reportCount;
    }
}
