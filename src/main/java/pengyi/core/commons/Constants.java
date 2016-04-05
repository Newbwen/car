package pengyi.core.commons;

/**
 * Created by YJH on 2016/3/7.
 */
public class Constants {

    public final static String SESSION_USER = "sessionUser";

    public final static String ALIPAY_PARTNER = "2088221376498325";

//    public final static String ALIPAY_RSA_PRIVATE_KEY = "MIICXQIBAAKBgQCrtHnOlQNS2X+16IrzaFH1wV1iq+iG0p0/uE8kj2E9+KAqX1MHVacW6PzYLr05bIWh/seovE+pyoxxJ0nMqquT/Xyo7IxX2CieyfyioaqwpSdpdU+EENIcvUVrLA+o1VxZiBIRLLE6rfNbRgbhe7LeHkNONmso+FzDlkHkckOZHQIDAQABAoGBAJ8rybMAnjkiWOp37L9h2ICaRQigVgkNmyojoHJC3CFHpqpA/cYR17g8OY/qHA3slSCr/8hi8PJ0SqbmhdpN6IjdQ1fpglM0DAYmHjTuYptMVyaGsGxR/K3ZjgJV0d8b42h78KLcpxoq0piZQTcdi1EYdCy1/ZqghjiHr4WZ10adAkEA4SVV4ldB5O+1j5YV8iJFuoxwGrtb36OacsqA/5QPpvlQKAB39lKx4jEgz/UaXiDO+oMi4ZdqH3BrwloFFtiHGwJBAMM8Tgr+jVrBBzACBWdkAOKdOHXKpCSe0dIm5HGYrqScXie2jVYzSf+MDvNHUh7uBbOlYa0DCdTCuSqjSakxTCcCQQCXeUL68LHIkO2aGOPS7/PxuiKCGaOKJsv2hv+a88ZEFGA1SXtsId7Dy0HEJ6cBxRj7udZx6Cjh1gTp0R6zbCIZAkA4PB2KoymaE3F7jYl6714l5/1ESHshJTHwo003vy/GsFeafsWVJ67P0crYnhl0WUEBLsjEqdRiGTh2pJPqDQ4PAkAilkqhs4PtGrcelunvG7G5+ikMYcYPU6xt3DjLmMkdLdMNoRN/hjmQONDkLOFNNrg+/4YLxF5c5bb5dLzW6DXE";

    public final static String ALIPAY_RSA_PRIVATE_KEY = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAN82s3px7AeyAADSjS56IsEkEo+OhVNpSR1356ZjD+7rXKD+8K1phjUbKV796i3gT/M1jRnFMNPypgJsdn0B6RLZ+J9YgyZNgR1IkJDKOUGJO2vNHa9fxo+RhVz76cZjHo7op7inliD9sE0lAblJ5LDXZ3307tcbqAFD9KKSVuTnAgMBAAECgYBvla7fVTgDGM67moYNZ+0b1Gaa8UphSk7MtnZNTVRXwb0KlqfGaOw4fB2QaSq6SFpvYKPq8BLawYCP0ZziHSRf4xXqxO2XvotiW9LehNu5ca72wdBeHwf4Bn1mZe9YvUivc/3g5g8HAr4XwxBvtZLfo3BYZqEoNwAZzdpDfe8YkQJBAPVjaOW4CnRB6gKkqFsW7wuZQvdGQwWm22Fh1KSgRD/0eSBewfEz8JucwbdyEigSCZZVGtZNsf4aKJSuMrVzJPsCQQDo3c4kNrOMFVOBBDjjc0muZ6E17a2GYZkp9GRa7jCCE69CIAMDFe6rdMb6CddQD6ewd6QvutShd2YF/l3LpcQFAkB7XcL62o/G6uxu78EEKn97YU3dayF+0egxCTISodAuJwZOU9VFIkuwsBpj06F1K7xOK/MWEnGNwjDsrrcnqf/JAkBNMJkQcAF8QeV4MsoYuzKFWVya37pRfTCkM5rAeYwi0huaM7pL341J0F/UqkZxB39yg3YtTCUHkOiEvBijVMNxAkEAyI1uCo+VM6MPcpwRAfUW+E9mWzytVtIuosegwD9qflHh2TyLekr9FRKEPvFVGDodn6EnpJOsteOoog8hmTH1AA==";

    public final static String ALIPAY_NOTIFY_VERIFY_URL = "https://mapi.alipay.com/gateway.do";

    public final static String ALIPAY_NOTIFY_VERIFY_PARAM = "service=notify_verify&partner=" + Constants.ALIPAY_PARTNER + "&notify_id=";

    public static String WECHAT_KEY = "";          //微信支付key
    public static String WECHAT_APPID = "";        //appid
    public static String WECHAT_MCH_ID ="";         //商户id

    public static String WECHAT_UNIFIED_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";

    public static String WECHAT_NOTIFY_URL = "http://a.xiguaxing.com/pay/wechat/notify";
}
