package pengyi.application.area.command;

/**
 * Created by YJH on 2016/3/9.
 */
public class CreateAreaCommand {

    private String name;                    //地区名
    private String priority;                //区域优先级
    private String parent;                    //父级地区

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }
}
