package pengyi.application.user.driver.command;

import pengyi.application.user.command.BaseCreateBaseUserCommand;
import pengyi.core.type.DriverType;
import pengyi.core.type.Sex;

/**
 * Created by YJH on 2016/3/15.
 */
public class CreateDriverCommand extends BaseCreateBaseUserCommand {

    private String name;                    //姓名
    private String head;                    //头像
    private String company;                //公司
    private Sex sex;                        //性别（0为男，1为女）
    private DriverType driverType;                       //类型（1代驾、2专车、3出租车）
    private String identityCardPic;         //身份证照片
    private String drivingLicencePic;       //驾驶证照片

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

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public DriverType getDriverType() {
        return driverType;
    }

    public void setDriverType(DriverType driverType) {
        this.driverType = driverType;
    }

    public String getIdentityCardPic() {
        return identityCardPic;
    }

    public void setIdentityCardPic(String identityCardPic) {
        this.identityCardPic = identityCardPic;
    }

    public String getDrivingLicencePic() {
        return drivingLicencePic;
    }

    public void setDrivingLicencePic(String drivingLicencePic) {
        this.drivingLicencePic = drivingLicencePic;
    }
}
