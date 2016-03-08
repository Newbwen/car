package pengyi.application.user.driver.command;

import pengyi.application.user.command.BaseEditBaseUserCommand;

import java.math.BigDecimal;

/**
 * Created by YJH on 2016/3/7.
 */
public class EditDriverCommand extends BaseEditBaseUserCommand {

    private String name;                    //姓名
    private String head;                    //头像
    private String company;                //公司
    private int sex;                        //性别（0为男，1为女）
    private Double level;                   //等级
    private int reportCount;                //举报次数
    private int driverType;                       //类型（1代驾、2专车、3出租车）

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

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public Double getLevel() {
        return level;
    }

    public void setLevel(Double level) {
        this.level = level;
    }

    public int getReportCount() {
        return reportCount;
    }

    public void setReportCount(int reportCount) {
        this.reportCount = reportCount;
    }

    public int getDriverType() {
        return driverType;
    }

    public void setDriverType(int driverType) {
        this.driverType = driverType;
    }
}
