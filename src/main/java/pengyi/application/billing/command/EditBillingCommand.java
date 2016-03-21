package pengyi.application.billing.command;

import java.math.BigDecimal;

/**
 * Created by YJH on 2016/3/21.
 */
public class EditBillingCommand {

    private String id;
    private Integer version;

    private BigDecimal KMBilling;
    private BigDecimal MinuteBilling;
    private String area;

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

    public BigDecimal getKMBilling() {
        return KMBilling;
    }

    public void setKMBilling(BigDecimal KMBilling) {
        this.KMBilling = KMBilling;
    }

    public BigDecimal getMinuteBilling() {
        return MinuteBilling;
    }

    public void setMinuteBilling(BigDecimal minuteBilling) {
        MinuteBilling = minuteBilling;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
}
