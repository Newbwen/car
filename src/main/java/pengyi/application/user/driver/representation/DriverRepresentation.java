package pengyi.application.user.driver.representation;

import pengyi.application.user.company.representation.CompanyRepresentation;
import pengyi.application.user.representation.BaseUserRepresentation;

import java.math.BigDecimal;

/**
 * Created by YJH on 2016/3/7.
 */
public class DriverRepresentation extends BaseUserRepresentation {

    private String name;                    //姓名
    private String head;                    //头像
    private CompanyRepresentation company;                //公司
    private int sex;                        //性别（0为男，1为女）
    private BigDecimal money;               //余额
    private Double level;                   //等级
    private Double longitude;               //经度
    private Double latitude;                //纬度
    private Boolean status;                 //是否启用
    private int reportCount;                //举报次数
    private Boolean online;                 //是否在线
    private int type;                       //类型（1代驾、2专车、3出租车）

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

    public CompanyRepresentation getCompany() {
        return company;
    }

    public void setCompany(CompanyRepresentation company) {
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

    @Override
    public Boolean getStatus() {
        return status;
    }

    @Override
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

    @Override
    public int getType() {
        return type;
    }

    @Override
    public void setType(int type) {
        this.type = type;
    }
}
