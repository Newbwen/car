package pengyi.application.billing.command;

import org.hibernate.validator.constraints.NotEmpty;
import pengyi.core.type.CarType;
import pengyi.core.type.DriverType;

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
    @NotNull(message = "{billing.startingPrice.NotNull.message}")
    private BigDecimal startingPrice;   //起步价
    @NotEmpty(message = "{billing.company.NotEmpty.message}")
    private String company;  //公司

    private DriverType driverType;
    private CarType carType;

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
