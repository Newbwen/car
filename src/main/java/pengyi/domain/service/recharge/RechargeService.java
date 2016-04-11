package pengyi.domain.service.recharge;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;
import pengyi.application.recharge.command.CreateRechargeCommand;
import pengyi.application.recharge.representation.RechargeRepresentation;
import pengyi.core.commons.Constants;
import pengyi.core.exception.WechatSignException;
import pengyi.core.pay.wechat.UnifiedRequest;
import pengyi.core.pay.wechat.UnifiedResponse;
import pengyi.core.util.CoreDateUtils;
import pengyi.core.util.HttpUtil;
import pengyi.core.util.Signature;
import pengyi.core.util.XMLParser;
import pengyi.domain.model.pay.AlipayNotify;
import pengyi.domain.model.pay.WechatNotify;
import pengyi.domain.model.recharge.IRechargeRepository;
import pengyi.domain.model.recharge.Recharge;
import pengyi.domain.model.user.BaseUser;
import pengyi.domain.service.user.IBaseUserService;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by pengyi on 2016/4/11.
 */
@Service("rechargeService")
public class RechargeService implements IRechargeService {

    @Autowired
    private IRechargeRepository<Recharge, String> rechargeRepository;

    @Autowired
    private IBaseUserService baseUserService;

    @Override
    public UnifiedResponse wechatPay(CreateRechargeCommand command) {

        BaseUser baseUser = baseUserService.searchByUserName(command.getUserName());
        if (null != baseUser) {
            Recharge recharge = new Recharge(baseUser, command.getMoney(), command.getPayType());
            rechargeRepository.save(recharge);

            String body = "余额充值：" + recharge.getId();
            String detail = command.getUserName() + "充值￥" + command.getMoney();
            UnifiedRequest request = new UnifiedRequest(body, detail, recharge.getId(), command.getMoney()
                    .multiply(new BigDecimal(100)).intValue(), command.getIp(), Constants.WECHAT_RECHARGE_NOTIFY_URL);

            try {
                String sign = Signature.getSign(request.toMap());
                request.setSign(sign);
                XStream xStream = new XStream(new DomDriver("utf-8", new XmlFriendlyNameCoder("-_", "_")));
                String s = HttpUtil.urlConnection(Constants.WECHAT_UNIFIED_URL, xStream.toXML(request));

                UnifiedResponse response = null;
                response = (UnifiedResponse) XMLParser.getObjFromXML(s, UnifiedResponse.class);
                if (response != null) {
                    response.setTime_stamp(System.currentTimeMillis() / 1000);
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("appid", response.getAppid());
                    map.put("partnerid", response.getMch_id());
                    map.put("prepayid", response.getPrepay_id());
                    map.put("package", "Sign=WXPay");
                    map.put("noncestr", response.getNonce_str());
                    map.put("timestamp", response.getTime_stamp());
                    response.setSign(Signature.getSign(map));

                }
                return response;
            } catch (ParserConfigurationException e) {
                throw new WechatSignException("微信返回值签名失败");
            } catch (IOException e) {
                throw new WechatSignException("微信返回值签名失败");
            } catch (SAXException e) {
                throw new WechatSignException("微信返回值签名失败");
            } catch (Exception e) {
                throw new WechatSignException("未知错误");
            }
        }

        return null;
    }

    @Override
    public Recharge alipayPay(CreateRechargeCommand command) {
        BaseUser baseUser = baseUserService.searchByUserName(command.getUserName());
        if (null != baseUser) {
            Recharge recharge = new Recharge(baseUser, command.getMoney(), command.getPayType());
            rechargeRepository.save(recharge);
            return recharge;
        }
        return null;
    }

    @Override
    public void alipaySuccess(AlipayNotify notify) {

        Recharge recharge = rechargeRepository.getById(notify.getOut_trade_no());

        if (null != recharge && recharge.getMoney().toString().equals(notify.getTotal_fee())) {
            recharge.setPayTime(CoreDateUtils.parseLongDate(notify.getGmt_payment()));
            recharge.setPayd(true);
            recharge.setPayNo(notify.getTrade_no());
        }

        rechargeRepository.update(recharge);
    }

    @Override
    public void wechatSuccess(WechatNotify notify) {

        Recharge recharge = rechargeRepository.getById(notify.getOut_trade_no());

        if (null != recharge && recharge.getMoney().toString().equals(notify.getTotal_fee())) {
            recharge.setPayTime(CoreDateUtils.parseDate(notify.getTime_end(), "yyyyMMddHHmmss"));
            recharge.setPayd(true);
            recharge.setPayNo(notify.getTransaction_id());
        }

        rechargeRepository.update(recharge);
    }

}