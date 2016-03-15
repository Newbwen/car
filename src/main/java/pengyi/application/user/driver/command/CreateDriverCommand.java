package pengyi.application.user.driver.command;

import pengyi.application.user.command.BaseCreateBaseUserCommand;
import pengyi.core.type.DriverType;
import pengyi.core.type.Sex;
import pengyi.domain.model.user.company.Company;

import java.math.BigDecimal;

/**
 * Created by YJH on 2016/3/15.
 */
public class CreateDriverCommand extends BaseCreateBaseUserCommand {

    private String name;                    //姓名
    private String head;                    //头像
    private Company company;                //公司
    private Sex sex;                        //性别（0为男，1为女）
    private BigDecimal money;               //余额
    private Double level;                   //等级
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
}
