package pengyi.application.user.terrace.command;

import pengyi.application.user.command.BaseCreateBaseUserCommand;

/**
 * Created by YJH on 2016/3/7.
 */
public class CreateTerraceCommand extends BaseCreateBaseUserCommand {


    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
