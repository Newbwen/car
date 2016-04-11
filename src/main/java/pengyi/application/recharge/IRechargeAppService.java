package pengyi.application.recharge;

import pengyi.application.recharge.command.CreateRechargeCommand;
import pengyi.core.api.BaseResponse;
import pengyi.domain.model.pay.AlipayNotify;
import pengyi.domain.model.pay.WechatNotify;

/**
 * Created by pengyi on 2016/4/11.
 */
public interface IRechargeAppService {

    BaseResponse wechatPay(CreateRechargeCommand command);

    BaseResponse alipayPay(CreateRechargeCommand command);

    void alipaySuccess(AlipayNotify notify);

    void wechatSuccess(WechatNotify notify);
}
