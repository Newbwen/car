package pengyi.domain.model.user.driver;

import pengyi.core.type.DriverType;
import pengyi.core.type.EnableStatus;
import pengyi.core.type.Sex;
import pengyi.core.type.UserType;
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
    private Sex sex;                        //性别（0为男，1为女）
    private BigDecimal money;               //余额
    private Double level;                   //评级（12345）
    private Double longitude;               //经度
    private Double latitude;                //纬度
    private Integer reportCount;                //举报次数
    private Boolean online;                 //是否在线
    private DriverType driverType;                       //类型（1代驾、2专车、3出租车）

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

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
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

    public Integer getReportCount() {
        return reportCount;
    }

    public void setReportCount(Integer reportCount) {
        this.reportCount = reportCount;
    }

    public Boolean getOnline() {
        return online;
    }

    public void setOnline(Boolean online) {
        this.online = online;
    }

    public DriverType getDriverType() {
        return driverType;
    }

    public void setDriverType(DriverType driverType) {
        this.driverType = driverType;
    }

    public Driver() {
        super();
    }

    public Driver(String name, String head, Company company, Sex sex, BigDecimal money, Double level, Double longitude, Double latitude, Integer reportCount, Boolean online, DriverType driverType) {
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

    public Driver(String userName, String password, String salt, EnableStatus status, BigDecimal balance, Date createDate, Role userRole, String email, UserType userType, String name, String head, Company company, Sex sex, BigDecimal money, Double level, Double longitude, Double latitude, Integer reportCount, Boolean online, DriverType driverType) {
        super(userName, password, salt, status, balance, createDate, userRole, email, userType);
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

    public Driver(String userName, String password, String salt, EnableStatus status, BigDecimal balance, Date createDate, Role userRole, String email, UserType userType){
        super(userName, password, salt, status, balance, createDate, userRole, email, userType);
    }
}
