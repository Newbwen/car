package pengyi.application.user.driver.command;

import pengyi.application.user.command.BaseEditBaseUserCommand;
import pengyi.core.type.DriverType;
import pengyi.core.type.Sex;

import java.util.Date;

/**
 * Created by YJH on 2016/3/7.
 */
public class EditDriverCommand extends BaseEditBaseUserCommand {

    private String name;                    //姓名
    private Sex sex;                        //性别（0为男，1为女）
    private DriverType driverType;                       //类型

    private String travelPic;               //行驶证
    private String drivingLicenceType;      //驾驶证类型（C1,C2,B1,B2,A1,A2）
    private Date awardDrivingLicenceDate;   //驾驶证发证日期
    private String phone;               //电话
    private String businessPic;         //营业资格证
    private String workPic;             //从业资格证

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getTravelPic() {
        return travelPic;
    }

    public void setTravelPic(String travelPic) {
        this.travelPic = travelPic;
    }

    public String getDrivingLicenceType() {
        return drivingLicenceType;
    }

    public void setDrivingLicenceType(String drivingLicenceType) {
        this.drivingLicenceType = drivingLicenceType;
    }

    public Date getAwardDrivingLicenceDate() {
        return awardDrivingLicenceDate;
    }

    public void setAwardDrivingLicenceDate(Date awardDrivingLicenceDate) {
        this.awardDrivingLicenceDate = awardDrivingLicenceDate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBusinessPic() {
        return businessPic;
    }

    public void setBusinessPic(String businessPic) {
        this.businessPic = businessPic;
    }

    public String getWorkPic() {
        return workPic;
    }

    public void setWorkPic(String workPic) {
        this.workPic = workPic;
    }
}
