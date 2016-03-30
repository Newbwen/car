package pengyi.domain.model.pay;

/**
 * Created by pengyi on 2016/3/30.
 */
public class DiscountGoodsDetail {

    private String goods_id;
    private String goods_name;
    private String discount_amount;
    private String voucher_id;

    public void setGoods_id(String goods_id) {
        this.goods_id = goods_id;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public void setDiscount_amount(String discount_amount) {
        this.discount_amount = discount_amount;
    }

    public void setVoucher_id(String voucher_id) {
        this.voucher_id = voucher_id;
    }

    public String getGoods_id() {
        return goods_id;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public String getDiscount_amount() {
        return discount_amount;
    }

    public String getVoucher_id() {
        return voucher_id;
    }

    public DiscountGoodsDetail() {
    }

    public DiscountGoodsDetail(String goods_id, String goods_name, String discount_amount, String voucher_id) {
        this.goods_id = goods_id;
        this.goods_name = goods_name;
        this.discount_amount = discount_amount;
        this.voucher_id = voucher_id;
    }
}
