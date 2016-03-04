package pengyi.domain.model.user;

import pengyi.domain.model.base.Identity;
import pengyi.domain.model.role.Role;

import java.math.BigDecimal;

/**
 * Created by pengyi on 2015/12/24.
 */
public class BaseUser extends Identity {

    private String phone;                           //手机号
    private Boolean status;                         //是否启用
    private BigDecimal balance;                     //余额
    private String createDate;                      //创建时间
    private Role userRole;                          //用户角色
    private String email;                           //邮箱
    private int type;                               //1平台、2用户、3公司、4司机

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public Role getUserRole() {
        return userRole;
    }

    public void setUserRole(Role userRole) {
        this.userRole = userRole;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public BaseUser() {
        super();
    }

    public BaseUser(String phone, Boolean status, BigDecimal balance, String createDate, Role userRole, String email, int type) {
        super();
        this.phone = phone;
        this.status = status;
        this.balance = balance;
        this.createDate = createDate;
        this.userRole = userRole;
        this.email = email;
        this.type = type;
    }
}
