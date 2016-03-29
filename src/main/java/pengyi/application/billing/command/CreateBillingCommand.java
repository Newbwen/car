package pengyi.application.billing.command;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Created by YJH on 2016/3/21.
 */
public class CreateBillingCommand {

    @NotNull(message = "{billing.kmBilling.NotNull.message}")
    private BigDecimal kmBilling;
    @NotNull(message = "{billing.minuteBilling.NotNull.message}")
    private BigDecimal minuteBilling;
    @NotEmpty(message = "{billing.company.NotEmpty.message}")
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
