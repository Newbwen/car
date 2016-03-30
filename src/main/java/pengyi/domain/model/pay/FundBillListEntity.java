package pengyi.domain.model.pay;

/**
 * Created by pengyi on 2016/3/30.
 */
public class FundBillListEntity {

    private int amount;
    private String fund_channel;

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setFund_channel(String fund_channel) {
        this.fund_channel = fund_channel;
    }

    public int getAmount() {
        return amount;
    }

    public String getFund_channel() {
        return fund_channel;
    }

    public FundBillListEntity() {
    }

    public FundBillListEntity(int amount, String fund_channel) {
        this.amount = amount;
        this.fund_channel = fund_channel;
    }
}
