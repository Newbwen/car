package pengyi.core.api;

/**
 * Created by YJH on 2016/3/15.
 */
public enum ResponseMessage {

    ERROR_10000(10000, "id字段不能为空"),
    ERROR_10001(10001, "version字段不能为空"),
    ERROR_10002(10002, "name字段不能为空"),
    ERROR_10003(10003, "registerAddress字段不能为空"),
    ERROR_10004(10004, "operateAddress字段不能为空"),
    ERROR_10005(10005, "folder字段不能为空"),
    ERROR_10006(10006, "company字段不能为空"),
    ERROR_20000(10000,"派送救援司机不能为空"),
    ERROR_20001(10001,"救援状态不能为空"),
    ERROR_20002(10002,"救援申请人不能为空"),
    ERROR_20003(10003,"救援申请人不能为空"),
    ERROR_20004(10004,"已安排救援不能取消");


    private int code;
    private String message;

    ResponseMessage(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
