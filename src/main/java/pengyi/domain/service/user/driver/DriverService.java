package pengyi.domain.service.user.driver;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pengyi.application.user.command.UpdateHeadPicCommand;
import pengyi.application.user.driver.command.*;
import pengyi.core.commons.PasswordHelper;
import pengyi.core.commons.command.EditStatusCommand;
import pengyi.core.exception.ExistException;
import pengyi.core.exception.NoFoundException;
import pengyi.core.type.EnableStatus;
import pengyi.core.type.UserType;
import pengyi.core.util.CoreStringUtils;
import pengyi.domain.model.role.Role;
import pengyi.domain.model.user.BaseUser;
import pengyi.domain.model.user.company.Company;
import pengyi.domain.model.user.driver.Driver;
import pengyi.domain.model.user.driver.IDriverRepository;
import pengyi.domain.service.role.IRoleService;
import pengyi.domain.service.user.IBaseUserService;
import pengyi.domain.service.user.company.ICompanyService;
import pengyi.repository.generic.Pagination;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    public Pagination<Driver> pagination(BaseListDriverCommand command) {
        List<Criterion> criteriaList = new ArrayList();
        if (!CoreStringUtils.isEmpty(command.getUserName())) {
            criteriaList.add(Restrictions.like("userName", command.getUserName(), MatchMode.ANYWHERE));
        }

        if (!CoreStringUtils.isEmpty(command.getName())) {
            criteriaList.add(Restrictions.like("name", command.getName(), MatchMode.ANYWHERE));
        }

        if (null != command.getStatus()) {
            criteriaList.add(Restrictions.eq("status", command.getStatus()));
        }
        return driverRepository.pagination(command.getPage(), command.getPageSize(), criteriaList, null);
    }

    @Override
    public Driver edit(EditDriverCommand command) {
        Driver driver = this.show(command.getId());
        driver.fainWhenConcurrencyViolation(command.getVersion());

//        Company company = companyService.show(command.getCompany());

        driver.setEmail(command.getEmail());
        driver.setName(command.getName());
//        driver.setHead(command.getHead());
//        driver.setCompany(company);
        driver.setSex(command.getSex());
//        driver.setLevel(command.getLevel());
//        driver.setReportCount(command.getReportCount());
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

    @Override
    public Driver create(Driver driver) {
        driverRepository.save(driver);
        return driver;
    }

    @Override
    public List<Driver> searchByCompany(String company) {
        return driverRepository.searchByCompany(company);
    }

    @Override
    public void updateDriverLevel(String driverId, Double level) {
        Driver driver = this.show(driverId);
        driver.setLevel(level);
        driverRepository.update(driver);
    }

    @Override
    public Pagination<Driver> apiPagination(CompanyDriverListCommand command) {
        List<Criterion> criterionList = new ArrayList<Criterion>();
        criterionList.add(Restrictions.eq("company.id", command.getCompany()));

        if (!CoreStringUtils.isEmpty(command.getDriverName())) {
            criterionList.add(Restrictions.like("userName", command.getDriverName()));
        }

        return driverRepository.pagination(command.getPage(), command.getPageSize(), criterionList, null);
    }

    @Override
    public Driver apiCompanyEditDriver(CompanyDriverEditCommand command) {
        Driver driver = this.show(command.getId());
        driver.fainWhenConcurrencyViolation(command.getVersion());

        driver.setName(command.getName());
        driver.setSex(command.getSex());
        driver.setDriverType(command.getDriverType());

        driverRepository.update(driver);
        return driver;
    }

    @Override
    public Driver apiCompanyAuditingDriver(CompanyAuditingDriverCommand command) {
        Company company = companyService.show(command.getCompany());
        Driver driver = this.show(command.getDriver());
        driver.setCompany(company);
        driverRepository.update(driver);
        return driver;
    }

    @Override
    public Driver apiCompanyEditStatusDriver(EditStatusCommand command) {
        Driver driver = this.show(command.getId());
        driver.fainWhenConcurrencyViolation(command.getVersion());

        if (driver.getStatus() == EnableStatus.ENABLE) {
            driver.setStatus(EnableStatus.DISABLE);
        } else {
            driver.setStatus(EnableStatus.ENABLE);
        }

        driverRepository.update(driver);

        return driver;
    }

    @Override
    public Driver apiCompanyCreateDriver(CreateDriverCommand command) {
        BaseUser baseUser = baseUserService.searchByUserName(command.getUserName());
        if (null != baseUser) {
            throw new ExistException("用户名[" + command.getUserName() + "]已存在");
        }
        Company company = companyService.show(command.getCompany());

        Role role = roleService.searchByName("driver");

        String salt = PasswordHelper.getSalt();
        String password = PasswordHelper.encryptPassword(command.getPassword(), command.getUserName() + salt);

        Driver driver = new Driver(command.getName(), password, salt, command.getStatus(), new BigDecimal(0),
                new Date(), role, command.getEmail(), UserType.DRIVER, command.getName(), null, company,
                command.getSex(), new BigDecimal(0), 0.0, 0.0, 0.0, 0, false, command.getDriverType());

        driverRepository.save(driver);

        return driver;
    }

    @Override
    public Driver apiCompanyExpelDriver(EditStatusCommand command) {
        Driver driver = this.show(command.getId());
        driver.fainWhenConcurrencyViolation(command.getVersion());

        driver.setCompany(null);

        driverRepository.update(driver);
        return driver;
    }

    @Override
    public Driver apiRegister(RegisterDriverCommand command) {
        BaseUser baseUser = baseUserService.searchByUserName(command.getUserName());
        if (null != baseUser) {
            throw new ExistException("用户名[" + command.getUserName() + "]已存在");
        }
        String salt = PasswordHelper.getSalt();
        String password = PasswordHelper.encryptPassword(command.getPassword(), command.getUserName() + salt);

        Role role = roleService.searchByName("driver");
        Driver driver = new Driver(command.getUserName(), password, salt, EnableStatus.ENABLE, new BigDecimal(0), new Date(), role, null, UserType.DRIVER,
                null, null, null, null, new BigDecimal(0), 0.0, 0.0, 0.0, 0, false, null);

        driverRepository.save(driver);
        return driver;
    }

    @Override
    public Driver apiEdit(EditDriverCommand command) {
        Driver driver = this.show(command.getId());
        driver.fainWhenConcurrencyViolation(command.getVersion());

        driver.setEmail(command.getEmail());
        driver.setName(command.getName());
        driver.setSex(command.getSex());
        driver.setDriverType(command.getDriverType());

        driverRepository.update(driver);

        return driver;
    }

    @Override
    public Driver apiUpdateHeadPic(UpdateHeadPicCommand command) {
        Driver driver = this.show(command.getId());
        driver.setHead(command.getHeadPic());
        driverRepository.update(driver);
        return driver;
    }
}
