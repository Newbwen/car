package pengyi.application.billing.command;

import java.math.BigDecimal;

/**
 * Created by YJH on 2016/3/21.
 */
public class EditBillingCommand {

    private String id;
    private Integer version;

    private BigDecimal kmBilling;
    private BigDecimal MinuteBilling;
    private String company;

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
        return MinuteBilling;
    }

    public void setMinuteBilling(BigDecimal minuteBilling) {
        MinuteBilling = minuteBilling;
    }
}
