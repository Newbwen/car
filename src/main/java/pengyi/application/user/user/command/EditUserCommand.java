package pengyi.application.user.user.command;

import pengyi.application.user.command.BaseEditBaseUserCommand;

import java.math.BigDecimal;

/**
 * Created by YJH on 2016/3/7.
 */
public class EditUserCommand extends BaseEditBaseUserCommand {

    private String name;                    //用户名
    private String head;                    //头像
    private int sex;                        //性别（0为男，2为女）

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

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }
}
