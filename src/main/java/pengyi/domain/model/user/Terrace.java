package pengyi.domain.model.user;

import pengyi.domain.model.base.Identity;

/**
 * 平台
 * Created by pengyi on 2016/3/4.
 */
public class Terrace extends Identity {

    private BaseUser baseUser;                  //用户
    private String name;                        //用户名

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

    public Terrace() {
        super();
    }

    public Terrace(BaseUser baseUser, String name) {
        super();
        this.baseUser = baseUser;
        this.name = name;
    }
}
