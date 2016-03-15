package pengyi.core.api;

/**
 * Created by pengyi on 2016/1/18.
 */
public class BaseResponse {

    private int code;                                                                       //错误码
    private long debug_time;                                                                //后台处理时间
    private Object obj;                                                                     //返回对象

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public long getDebug_time() {
        return debug_time;
    }

    public void setDebug_time(long debug_time) {
        this.debug_time = debug_time;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    public BaseResponse(int code, long debug_time, Object obj) {
        this.code = code;
        this.debug_time = debug_time;
        this.obj = obj;
    }
}
