package pengyi.application.user.driver.command;

/**
 * Created by YJH on 2016/3/21.
 */
public class RegisterDriverCommand {

    private String userName;
    private String password;

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
}
