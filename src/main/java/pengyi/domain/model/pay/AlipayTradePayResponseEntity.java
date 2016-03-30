package pengyi.domain.model.pay;

import java.util.List;

/**
 * Created by pengyi on 2016/3/30.
 */
public class AlipayTradePayResponseEntity {

    private String buyer_logon_id;
    private double buyer_pay_amount;
    private String buyer_user_id;
    private double card_balance;
    private String code;
    private DiscountGoodsDetail discount_goods_detail;
    private String gmt_payment;
    private double invoice_amount;
    private String msg;
    private String open_id;
    private String out_trade_no;
    private double point_amount;
    private String receipt_amount;
    private String store_name;
    private double total_amount;
    private String trade_no;

    private List<FundBillListEntity> fund_bill_list;

    public void setBuyer_logon_id(String buyer_logon_id) {
        this.buyer_logon_id = buyer_logon_id;
    }

    public void setBuyer_pay_amount(double buyer_pay_amount) {
        this.buyer_pay_amount = buyer_pay_amount;
    }

    public void setBuyer_user_id(String buyer_user_id) {
        this.buyer_user_id = buyer_user_id;
    }

    public void setCard_balance(double card_balance) {
        this.card_balance = card_balance;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setDiscount_goods_detail(DiscountGoodsDetail discount_goods_detail) {
        this.discount_goods_detail = discount_goods_detail;
    }

    public void setGmt_payment(String gmt_payment) {
        this.gmt_payment = gmt_payment;
    }

    public void setInvoice_amount(double invoice_amount) {
        this.invoice_amount = invoice_amount;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setOpen_id(String open_id) {
        this.open_id = open_id;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public void setPoint_amount(double point_amount) {
        this.point_amount = point_amount;
    }

    public void setReceipt_amount(String receipt_amount) {
        this.receipt_amount = receipt_amount;
    }

    public void setStore_name(String store_name) {
        this.store_name = store_name;
    }

    public void setTotal_amount(double total_amount) {
        this.total_amount = total_amount;
    }

    public void setTrade_no(String trade_no) {
        this.trade_no = trade_no;
    }

    public void setFund_bill_list(List<FundBillListEntity> fund_bill_list) {
        this.fund_bill_list = fund_bill_list;
    }

    public String getBuyer_logon_id() {
        return buyer_logon_id;
    }

    public double getBuyer_pay_amount() {
        return buyer_pay_amount;
    }

    public String getBuyer_user_id() {
        return buyer_user_id;
    }

    public double getCard_balance() {
        return card_balance;
    }

    public String getCode() {
        return code;
    }

    public DiscountGoodsDetail getDiscount_goods_detail() {
        return discount_goods_detail;
    }

    public String getGmt_payment() {
        return gmt_payment;
    }

    public double getInvoice_amount() {
        return invoice_amount;
    }

    public String getMsg() {
        return msg;
    }

    public String getOpen_id() {
        return open_id;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public double getPoint_amount() {
        return point_amount;
    }

    public String getReceipt_amount() {
        return receipt_amount;
    }

    public String getStore_name() {
        return store_name;
    }

    public double getTotal_amount() {
        return total_amount;
    }

    public String getTrade_no() {
        return trade_no;
    }

    public List<FundBillListEntity> getFund_bill_list() {
        return fund_bill_list;
    }

    public AlipayTradePayResponseEntity() {
    }

    public AlipayTradePayResponseEntity(String buyer_logon_id, double buyer_pay_amount, String buyer_user_id, double card_balance, String code, DiscountGoodsDetail discount_goods_detail, String gmt_payment, double invoice_amount, String msg, String open_id, String out_trade_no, double point_amount, String receipt_amount, String store_name, double total_amount, String trade_no, List<FundBillListEntity> fund_bill_list) {
        this.buyer_logon_id = buyer_logon_id;
        this.buyer_pay_amount = buyer_pay_amount;
        this.buyer_user_id = buyer_user_id;
        this.card_balance = card_balance;
        this.code = code;
        this.discount_goods_detail = discount_goods_detail;
        this.gmt_payment = gmt_payment;
        this.invoice_amount = invoice_amount;
        this.msg = msg;
        this.open_id = open_id;
        this.out_trade_no = out_trade_no;
        this.point_amount = point_amount;
        this.receipt_amount = receipt_amount;
        this.store_name = store_name;
        this.total_amount = total_amount;
        this.trade_no = trade_no;
        this.fund_bill_list = fund_bill_list;
    }
}
