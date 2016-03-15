package pengyi.core.api;

/**
 * 返回码
 * Created by pengyi on 2016/1/18.
 */
public enum  ResponseCode {

    RESPONSE_CODE_SUCCESS(10000, "成功"),
    RESPONSE_CODE_FAILURE(90000, "未知错误"),

    RESPONSE_CODE_CONCURRENCY_ERROR(90001,"记录在提交之前已发生改变,请重新提交."),
    RESPONSE_CODE_PARAMETER_ERROR(90002,"参数错误");

    private int code;
    private String message;

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

    ResponseCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
