package pengyi.core.pay.wechat;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;
import org.xml.sax.SAXException;
import pengyi.core.commons.Constants;
import pengyi.core.util.HttpUtil;
import pengyi.core.util.Signature;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

/**
 * 微信支付
 * Created by pengyi on 2016/3/7.
 */
public class WechatPayHandle {

    public UnifiedResponse unifiedOrder(UnifiedRequest object) {

        try {

            String sign = Signature.getWechatSign(object);
            object.setSign(sign);
            XStream xStream=new XStream(new DomDriver("UTF-8", new XmlFriendlyNameCoder("-_", "_")));
            String s = HttpUtil.urlConnection(Constants.WECHAT_UNIFIED_URL, xStream.toXML(object));

            return (UnifiedResponse) xStream.fromXML(Signature.getSignFromResponseString(s));

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        return null;
    }

}
