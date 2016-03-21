package pengyi.application.billing.representation;

import pengyi.application.area.representation.AreaRepresentation;

import java.math.BigDecimal;

/**
 * Created by YJH on 2016/3/21.
 */
public class BillingRepresentation {

    private String id;
    private Integer version;

    private BigDecimal KMBilling;   //根据公里计费

    private BigDecimal minuteBilling; //根据分钟计费

    private AreaRepresentation area;  //区域

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
        return minuteBilling;
    }

    public void setMinuteBilling(BigDecimal minuteBilling) {
        this.minuteBilling = minuteBilling;
    }

    public AreaRepresentation getArea() {
        return area;
    }

    public void setArea(AreaRepresentation area) {
        this.area = area;
    }
}
