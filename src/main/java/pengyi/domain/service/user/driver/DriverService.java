package pengyi.domain.service.user.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pengyi.application.user.driver.command.CreateDriverCommand;
import pengyi.application.user.driver.command.EditDriverCommand;
import pengyi.core.commons.PasswordHelper;
import pengyi.core.exception.ExistException;
import pengyi.core.exception.NoFoundException;
import pengyi.core.type.UserType;
import pengyi.domain.model.role.Role;
import pengyi.domain.model.user.company.Company;
import pengyi.domain.model.user.driver.Driver;
import pengyi.domain.model.user.driver.IDriverRepository;
import pengyi.domain.service.role.IRoleService;
import pengyi.domain.service.user.IBaseUserService;
import pengyi.domain.service.user.company.ICompanyService;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by YJH on 2016/3/7.
 */
@Service("driverService")
public class DriverService implements IDriverService {

    @Autowired
    private IDriverRepository<Driver, String> driverRepository;

    @Autowired
    private IBaseUserService baseUserService;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private ICompanyService companyService;

    @Override
    public Driver create(CreateDriverCommand command) {
        if (null != baseUserService.searchByPhone(command.getPhone())) {
            throw new ExistException("用户名[" + command.getPhone() + "]已存在");
        }

        String salt = PasswordHelper.getSalt();
        String password = PasswordHelper.encryptPassword(command.getPassword(), command.getPhone() + salt);

        Company company = companyService.show(command.getCompany());

        Role role = roleService.searchByName("driver");

        Driver driver = new Driver(command.getPhone(), password, salt, command.getStatus(),
                new BigDecimal(0), new Date(), role, command.getEmail(), UserType.DRIVER, command.getName(),
                command.getHead(), company, command.getSex(), new BigDecimal(0), 0.0, 0.0, 0.0,
                0, false, command.getDriverType());

        driverRepository.save(driver);

        return driver;
    }

    @Override
    public Driver edit(EditDriverCommand command) {
        Driver driver = this.show(command.getId());
        driver.fainWhenConcurrencyViolation(command.getVersion());

        Company company = companyService.show(command.getCompany());

        driver.setEmail(command.getEmail());
        driver.setName(command.getName());
        driver.setHead(command.getHead());
        driver.setCompany(company);
        driver.setSex(command.getSex());
        driver.setLevel(command.getLevel());
        driver.setReportCount(command.getReportCount());
        driver.setDriverType(command.getDriverType());

        driverRepository.update(driver);

        return driver;
    }

    @Override
    public Driver show(String id) {
        Driver driver = driverRepository.getById(id);
        if (null == driver) {
            throw new NoFoundException("没有找到用户id=[" + id + "]的记录");
        }
        return driver;
    }
}
