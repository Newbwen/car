package pengyi.application.user.driver.command;

import pengyi.core.type.DriverType;

/**
 * Created by YJH on 2016/3/21.
 */
public class RegisterDriverCommand {

    private String userName;
    private String password;
    private String verificationCode;
    private String identityCardPic;
    private String drivingLicencePic;
    private String company;
    private DriverType driverType;
    private String startDriveDate;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
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

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public DriverType getDriverType() {
        return driverType;
    }

    public void setDriverType(DriverType driverType) {
        this.driverType = driverType;
    }

    public String getStartDriveDate() {
        return startDriveDate;
    }

    public void setStartDriveDate(String startDriveDate) {
        this.startDriveDate = startDriveDate;
    }
}
