package pengyi.domain.model.user;

import pengyi.domain.model.area.Area;
import pengyi.domain.model.base.Identity;

import java.math.BigDecimal;

/**
 * 公司
 * Created by pengyi on 2016/3/4.
 */
public class Company extends Identity {

    private BaseUser baseUser;      //用户
    private String name;            //公司名
    private String folder;          //公司资质
    private String registerDate;    //注册时间
    private Area registerAddress;   //注册地点
    private Area operateAddress;    //运营地点
    private BigDecimal money;       //余额
    private Double level;           //等级

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

    public String getFolder() {
        return folder;
    }

    public void setFolder(String folder) {
        this.folder = folder;
    }

    public String getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(String registerDate) {
        this.registerDate = registerDate;
    }

    public Area getRegisterAddress() {
        return registerAddress;
    }

    public void setRegisterAddress(Area registerAddress) {
        this.registerAddress = registerAddress;
    }

    public Area getOperateAddress() {
        return operateAddress;
    }

    public void setOperateAddress(Area operateAddress) {
        this.operateAddress = operateAddress;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public Double getLevel() {
        return level;
    }

    public void setLevel(Double level) {
        this.level = level;
    }

    public Company() {
        super();
    }

    public Company(BaseUser baseUser, String name, String folder, String registerDate, Area registerAddress, Area operateAddress, BigDecimal money, Double level) {
        super();
        this.baseUser = baseUser;
        this.name = name;
        this.folder = folder;
        this.registerDate = registerDate;
        this.registerAddress = registerAddress;
        this.operateAddress = operateAddress;
        this.money = money;
        this.level = level;
    }
}
