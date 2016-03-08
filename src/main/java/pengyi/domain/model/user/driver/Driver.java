package pengyi.domain.model.user.driver;

import pengyi.domain.model.role.Role;
import pengyi.domain.model.user.BaseUser;
import pengyi.domain.model.user.company.Company;

import java.math.BigDecimal;
import java.util.Date;


/**
 * update by yjh
 * <p>
 * 司机
 * Created by pengyi on 2016/3/4.
 */
public class Driver extends BaseUser {

    private String name;                    //姓名
    private String head;                    //头像
    private Company company;                //公司
    private int sex;                        //性别（0为男，1为女）
    private BigDecimal money;               //余额
    private Double level;                   //等级
    private Double longitude;               //经度
    private Double latitude;                //纬度
    private int reportCount;                //举报次数
    private Boolean online;                 //是否在线
    private int driverType;                       //类型（1代驾、2专车、3出租车）

    public int getDriverType() {
        return driverType;
    }

    public void setDriverType(int driverType) {
        this.driverType = driverType;
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

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
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

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public int getReportCount() {
        return reportCount;
    }

    public void setReportCount(int reportCount) {
        this.reportCount = reportCount;
    }

    public Boolean getOnline() {
        return online;
    }

    public void setOnline(Boolean online) {
        this.online = online;
    }

    public Driver() {
        super();
    }

    public Driver(String name, String head, Company company, int sex, BigDecimal money, Double level, Double longitude, Double latitude, int reportCount, Boolean online, int driverType) {
        this.name = name;
        this.head = head;
        this.company = company;
        this.sex = sex;
        this.money = money;
        this.level = level;
        this.longitude = longitude;
        this.latitude = latitude;
        this.reportCount = reportCount;
        this.online = online;
        this.driverType = driverType;
    }

    public Driver(String phone, String password, String salt, Boolean status, BigDecimal balance, Date createDate, Role userRole, String email, int type, String name, String head, Company company, int sex, BigDecimal money, Double level, Double longitude, Double latitude, int reportCount, Boolean online, int driverType) {
        super(phone, password, salt, status, balance, createDate, userRole, email, type);
        this.name = name;
        this.head = head;
        this.company = company;
        this.sex = sex;
        this.money = money;
        this.level = level;
        this.longitude = longitude;
        this.latitude = latitude;
        this.reportCount = reportCount;
        this.online = online;
        this.driverType = driverType;
    }
}
