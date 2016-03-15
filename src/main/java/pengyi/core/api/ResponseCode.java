package pengyi.core.api;

/**
 * 返回码
 * Created by pengyi on 2016/1/18.
 */
public enum  ResponseCode {

    RESPONSE_CODE_SUCCESS(10000, "成功"),
    RESPONSE_CODE_FAILURE(90000, "未知错误");

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
