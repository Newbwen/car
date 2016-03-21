package pengyi.application.billing.command;

import java.math.BigDecimal;

/**
 * Created by YJH on 2016/3/21.
 */
public class CreateBillingCommand {

    private BigDecimal KMBilling;
    private BigDecimal minuteBilling;
    private String area;

    public BigDecimal getKMBilling() {
        return KMBilling;
    }

    public void setKMBilling(BigDecimal KMBilling) {
        this.KMBilling = KMBilling;
    }

    public BigDecimal getMinuteBilling() {
        return minuteBilling;
    }

    public void setMinuteBilling(BigDecimal minuteBilling) {
        this.minuteBilling = minuteBilling;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
}
