package pengyi.domain.model.pay;


/**
 * Created by pengyi on 2016/3/30.
 */
public class AlipayNotify {

    private AlipayTradePayResponseEntity alipay_trade_pay_response;

    public void setAlipay_trade_pay_response(AlipayTradePayResponseEntity alipay_trade_pay_response) {
        this.alipay_trade_pay_response = alipay_trade_pay_response;
    }

    public AlipayTradePayResponseEntity getAlipay_trade_pay_response() {
        return alipay_trade_pay_response;
    }

    public AlipayNotify() {
    }

    public AlipayNotify(AlipayTradePayResponseEntity alipay_trade_pay_response) {
        this.alipay_trade_pay_response = alipay_trade_pay_response;
    }
}
