package pengyi.application.billing.command;

import java.math.BigDecimal;

/**
 * Created by YJH on 2016/3/21.
 */
public class CreateBillingCommand {

    private BigDecimal kmBilling;
    private BigDecimal minuteBilling;
    private String company;  //公司

    public BigDecimal getKmBilling() {
        return kmBilling;
    }

    public void setKmBilling(BigDecimal kmBilling) {
        this.kmBilling = kmBilling;
    }

    public BigDecimal getMinuteBilling() {
        return minuteBilling;
    }

    public void setMinuteBilling(BigDecimal minuteBilling) {
        this.minuteBilling = minuteBilling;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
