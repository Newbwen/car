package pengyi.application.user.representation;

import pengyi.application.role.representation.RoleRepresentation;
import pengyi.domain.model.role.Role;

import java.math.BigDecimal;

/**
 * Created by YJH on 2016/3/7.
 */
public class BaseUserRepresentation {

    private String id;
    private Integer version;

    private String phone;                           //手机号
    private String password;                        //密码
    private String salt;                            //密码盐
    private Boolean status;                         //是否启用(true=启用，false=禁用)
    private BigDecimal balance;                     //余额
    private String createDate;                      //创建时间
    private RoleRepresentation userRole;                          //用户角色
    private String email;                           //邮箱
    private int type;                               //1平台、2用户、3公司、4司机

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
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

    public RoleRepresentation getUserRole() {
        return userRole;
    }

    public void setUserRole(RoleRepresentation userRole) {
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
}
