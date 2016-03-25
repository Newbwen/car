package pengyi.domain.model.billing;

import pengyi.domain.model.base.Identity;
import pengyi.domain.model.user.company.Company;

import java.math.BigDecimal;

/**
 * Created by YJH on 2016/3/21.
 */
public class Billing extends Identity {

    private BigDecimal kmBilling;   //根据公里计费

    private BigDecimal minuteBilling; //根据分钟计费

    private Company company;  //区域

    public Billing() {
        super();
    }

    public Billing(BigDecimal kmBilling, BigDecimal minuteBilling, Company company) {
        this.kmBilling = kmBilling;
        this.minuteBilling = minuteBilling;
        this.company = company;
    }

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

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
