package pengyi.application.billing.representation;

import pengyi.application.user.company.representation.CompanyRepresentation;
import pengyi.core.type.CarType;
import pengyi.core.type.DriverType;

import java.math.BigDecimal;

/**
 * Created by YJH on 2016/3/21.
 */
public class BillingRepresentation {

    private String id;
    private Integer version;

    private BigDecimal kmBilling;   //根据公里计费

    private BigDecimal minuteBilling; //根据分钟计费

    private BigDecimal startingPrice;   //起步价

    private DriverType driverType;

    private CarType carType;

    private CompanyRepresentation company;

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

    public DriverType getDriverType() {
        return driverType;
    }

    public void setDriverType(DriverType driverType) {
        this.driverType = driverType;
    }

    public CarType getCarType() {
        return carType;
    }

    public void setCarType(CarType carType) {
        this.carType = carType;
    }

    public BigDecimal getStartingPrice() {
        return startingPrice;
    }

    public void setStartingPrice(BigDecimal startingPrice) {
        this.startingPrice = startingPrice;
    }
}
