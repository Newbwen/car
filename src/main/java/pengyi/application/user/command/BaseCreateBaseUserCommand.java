package pengyi.application.user.command;

import pengyi.domain.model.role.Role;

import java.math.BigDecimal;

/**
 * Created by YJH on 2016/3/7.
 */
public class BaseCreateBaseUserCommand {

    private String phone;                           //手机号
    private String password;                        //密码
    private Boolean status;                         //是否启用(true=启用，false=禁用)
    private String userRole;                        //用户角色
    private String email;                           //邮箱
    private int type;                               //1平台、2用户、3公司、4司机

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

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
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
