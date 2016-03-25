package pengyi.core.api;

/**
 * Created by YJH on 2016/3/15.
 */
public enum ResponseMessage {

    //注册
    ERROR_10000(10000, "id字段不能为空"),
    ERROR_10001(10001, "version字段不能为空"),
    ERROR_10002(10002, "name字段不能为空"),
    ERROR_10003(10003, "registerAddress字段不能为空"),
    ERROR_10004(10004, "operateAddress字段不能为空"),
    ERROR_10005(10005, "folder字段不能为空"),
    ERROR_10006(10006, "company字段不能为空"),
    ERROR_10007(10007, "sex字段不能为空"),
    ERROR_10008(10008, "driverType字段不能为空"),
    ERROR_10009(10009, "driver字段不能为空"),
    ERROR_10010(10010, "userName字段不能为空"),
    ERROR_10011(10011, "password字段不能为空"),
    ERROR_10012(10012, "status字段不能为空"),
    ERROR_10013(10013, "email字段不能为空"),
    ERROR_10014(10014, "registerDate字段不能为空"),
    ERROR_10015(10015, "registerDate字段格式错误(yyyy-MM-dd hh:mm:ss)"),
    ERROR_10016(10016, "oldPassword字段不能为空"),
    ERROR_10017(10017, "newPassword字段不能为空"),
    ERROR_10018(10018, "headPic字段不能为空"),
    ERROR_10019(10019, "verificationCode字段不能为空"),
    ERROR_10020(10020, "orderUser字段不能为空"),
    ERROR_10021(10021, "subscribeDate字段不能为空"),
    ERROR_10022(10022, "subscribeDate字段格式错误(yyyy-MM-dd hh:mm:ss)"),
    ERROR_10023(10023, "receiveUser字段不能为空"),
    ERROR_10024(10024, "orderId字段不能为空"),
    ERROR_10025(10025, "identityCardPic字段不能为空"),
    ERROR_10026(10026, "drivingLicencePic字段不能为空"),
    ERROR_10027(10027, "startAddress字段不能为空"),
    ERROR_10028(10028, "endAddress字段不能为空"),

    //救援
    ERROR_20000(20000, "派送救援司机不能为空"),
    ERROR_20001(20001, "救援状态不能为空"),
    ERROR_20002(20002, "救援申请人不能为空"),
    ERROR_20003(20003, "救援说明不能为空"),
    ERROR_20004(20004, "已安排救援不能取消"),
    ERROR_20005(20005, "applyUser字段不能为空"),
    ERROR_20006(20006, "rescueType字段不能为空"),
    ERROR_20007(20007, "description字段不能为空"),

    //车辆
    ERROR_30001(30001, "车牌号已存在"),

    //评价
    ERROR_40001(40001, "content字段不能为空"),
    ERROR_40002(40002, "level字段不能为空"),

    //站内信
    ERROR_50001(50001, "接收人sendBaseUser字段不能为空"),
    ERROR_50002(50002, "类容content字段不能为空"),
    ERROR_50003(50003, "类型type字段不能为空"),
    ERROR_50004(50004,"字段messageId不能为空");


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
