package pengyi.application.billing.representation;

import pengyi.application.user.company.representation.CompanyRepresentation;
import pengyi.repository.user.company.CompanyRepository;

import java.math.BigDecimal;

/**
 * Created by YJH on 2016/3/21.
 */
public class BillingRepresentation {

    private String id;
    private Integer version;

    private BigDecimal kmBilling;   //根据公里计费

    private BigDecimal minuteBilling; //根据分钟计费

    private CompanyRepresentation company;  //公司

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

    public BigDecimal getMinuteBilling() {
        return minuteBilling;
    }

    public void setMinuteBilling(BigDecimal minuteBilling) {
        this.minuteBilling = minuteBilling;
    }

    public CompanyRepresentation getCompany() {
        return company;
    }

    public void setCompany(CompanyRepresentation company) {
        this.company = company;
    }
}
