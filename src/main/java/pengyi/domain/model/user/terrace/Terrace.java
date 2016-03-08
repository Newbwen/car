package pengyi.domain.model.user.terrace;

import pengyi.domain.model.base.Identity;
import pengyi.domain.model.role.Role;
import pengyi.domain.model.user.BaseUser;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * update by yjh
 *
 * 平台
 * Created by pengyi on 2016/3/4.
 */
public class Terrace extends BaseUser {

    private String name;                        //用户名

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Terrace() {
        super();
    }

    public Terrace(String name) {
        this.name = name;
    }

    public Terrace(String phone, String password, String salt, Boolean status, BigDecimal balance, Date createDate, Role userRole, String email, int type, String name) {
        super(phone, password, salt, status, balance, createDate, userRole, email, type);
        this.name = name;
    }
}