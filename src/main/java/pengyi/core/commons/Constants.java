package pengyi.core.commons;

/**
 * Created by YJH on 2016/3/7.
 */
public class Constants {

    public final static String SESSION_USER = "sessionUser";

    public final static String ALIPAY_PARTNER = "2088221376498325";

    public final static String ALIPAY_NOTIFY_VERIFY_URL = "https://mapi.alipay.com/gateway.do";

    public final static String ALIPAY_NOTIFY_VERIFY_PARAM = "service=notify_verify&partner=" + Constants.ALIPAY_PARTNER + "&notify_id=";

    public static String WECHAT_KEY = "";          //微信支付key
    public static String WECHAT_APPID = "";        //appid
    public static String WECHAT_MCH_ID ="";         //商户id

    public static String WECHAT_UNIFIED_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";

    public static String WECHAT_NOTIFY_URL = "http://a.xiguaxing.com/pay/wechat/notify";
}
