package pengyi.core.api;

/**
 * Created by pengyi on 2016/1/18.
 */
public class BaseResponse {

    private ResponseCode code;                                                                       //错误码
    private long debug_time;                                                                //后台处理时间
    private Object data;                                                                     //返回对象

    public BaseResponse(ResponseCode code, long debug_time, Object data) {
        this.code = code;
        this.debug_time = debug_time;
        this.data = data;
    }

    public ResponseCode getCode() {
        return code;
    }

    public void setCode(ResponseCode code) {
        this.code = code;
    }

    public long getDebug_time() {
        return debug_time;
    }

    public void setDebug_time(long debug_time) {
        this.debug_time = debug_time;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
