package pengyi.domain.service.recharge;

import pengyi.application.recharge.command.CreateRechargeCommand;
import pengyi.core.pay.wechat.UnifiedResponse;
import pengyi.domain.model.pay.AlipayNotify;
import pengyi.domain.model.pay.WechatNotify;
import pengyi.domain.model.recharge.Recharge;

/**
 * Created by pengyi on 2016/4/11.
 */
public interface IRechargeService {

    UnifiedResponse wechatPay(CreateRechargeCommand command);

    Recharge alipayPay(CreateRechargeCommand command);

    void alipaySuccess(AlipayNotify notify);

    void wechatSuccess(WechatNotify notify);
}
