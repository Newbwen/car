package pengyi.domain.model.billing;

import pengyi.domain.model.area.Area;
import pengyi.domain.model.base.Identity;

import java.math.BigDecimal;

/**
 * Created by YJH on 2016/3/21.
 */
public class Billing extends Identity {

    private BigDecimal kmBilling;   //根据公里计费

    private BigDecimal minuteBilling; //根据分钟计费

    private Area area;  //区域

    public Billing() {
        super();
    }

    public Billing(BigDecimal kmBilling, BigDecimal minuteBilling, Area area) {
        this.kmBilling = kmBilling;
        this.minuteBilling = minuteBilling;
        this.area = area;
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

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }
}
