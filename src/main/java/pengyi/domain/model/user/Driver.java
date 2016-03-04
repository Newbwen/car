package pengyi.domain.model.user;

import pengyi.domain.model.base.Identity;

import java.math.BigDecimal;


/**
 * 司机
 * Created by pengyi on 2016/3/4.
 */
public class Driver extends Identity {

    private BaseUser baseUser;              //用户
    private String name;                    //姓名
    private String head;                    //头像
    private Company company;                //公司
    private int sex;                        //性别（0为男，1为女）
    private BigDecimal money;               //余额
    private Double level;                   //等级
    private Double longitude;               //经度
    private Double latitude;                //纬度
    private Boolean status;                 //是否启用
    private int reportCount;                //举报次数
    private Boolean online;                 //是否在线
    private int type;                       //类型（1代驾、2专车、3出租车）

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

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

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
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

    public Driver(BaseUser baseUser, String name, String head, Company company, int sex, BigDecimal money, Double level, Double longitude, Double latitude, Boolean status, int reportCount, Boolean online, int type) {
        super();
        this.baseUser = baseUser;
        this.name = name;
        this.head = head;
        this.company = company;
        this.sex = sex;
        this.money = money;
        this.level = level;
        this.longitude = longitude;
        this.latitude = latitude;
        this.status = status;
        this.reportCount = reportCount;
        this.online = online;
        this.type = type;
    }
}
