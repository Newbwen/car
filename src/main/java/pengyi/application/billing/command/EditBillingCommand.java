package pengyi.application.billing.command;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Created by YJH on 2016/3/21.
 */
public class EditBillingCommand {

    private String id;
    private Integer version;

    @NotNull(message = "{billing.kmBilling.NotNull.message}")
    private BigDecimal kmBilling;
    @NotNull(message = "{billing.minuteBilling.NotNull.message}")
    private BigDecimal minuteBilling;
    @NotEmpty(message = "{billing.company.NotEmpty.message}")
    private String company;  //公司

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public BigDecimal getKmBilling() {
        return kmBilling;
    }

    public void setKmBilling(BigDecimal kmBilling) {
        this.kmBilling = kmBilling;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public BigDecimal getMinuteBilling() {
        return minuteBilling;
    }

    public void setMinuteBilling(BigDecimal minuteBilling) {
        this.minuteBilling = minuteBilling;
    }
}
