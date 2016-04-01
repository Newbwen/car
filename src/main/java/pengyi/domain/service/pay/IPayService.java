package pengyi.domain.service.pay;


import pengyi.domain.model.pay.AlipayNotify;
import pengyi.domain.model.pay.WechatNotify;

/**
 * Created by YJH on 2016/3/7.
 */
public interface IPayService {

    void alipaySuccess(AlipayNotify notify);

    void wechatSuccess(WechatNotify notify);
}
