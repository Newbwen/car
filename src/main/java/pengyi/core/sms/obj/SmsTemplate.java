package pengyi.core.sms.obj;

/**
 * 短信模板
 * Created by pengyi on 2016/3/7.
 */
public enum  SmsTemplate {

    AUTHENTICATION("SMS_5391049", "验证码${code}，您正在进行${product}身份验证，打死不要告诉别人哦！"),   //身份验证
    SME_LOGIN("SMS_5391048", "验证码${code}，您正在登录${product}，若非本人操作，请勿泄露。"),            //登录验证
    REGISTER("SMS_5391046", "验证码${code}，您正在注册成为${product}用户，感谢您的支持！"),               //注册
    RESETPWD("SMS_5391044", "验证码${code}，您正在尝试修改${product}登录密码，请妥善保管账户信息。"),     //修改密码
    INFO_CHANGE("SMS_5391043", "验证码${code}，您正在尝试变更${product}重要信息，请妥善保管账户信息。");  //修改重要信息

    private String key;
    private String value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    SmsTemplate(String key, String value) {
        this.key = key;
        this.value = value;
    }
}
