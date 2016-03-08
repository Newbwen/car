package pengyi.domain.service.user.company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pengyi.application.user.company.command.CreateCompanyCommand;
import pengyi.application.user.company.command.EditCompanyCommand;
import pengyi.core.commons.PasswordHelper;
import pengyi.core.exception.ExistException;
import pengyi.core.exception.NoFoundException;
import pengyi.domain.model.area.Area;
import pengyi.domain.model.role.Role;
import pengyi.domain.model.user.company.Company;
import pengyi.domain.model.user.company.ICompanyRepository;
import pengyi.domain.service.area.IAreaService;
import pengyi.domain.service.role.IRoleService;
import pengyi.domain.service.user.IBaseUserService;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by YJH on 2016/3/7.
 */
@Service("companyService")
public class CompanyService implements ICompanyService {

    @Autowired
    private ICompanyRepository<Company, String> companyRepository;

    @Autowired
    private IBaseUserService baseUserService;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IAreaService areaService;

    @Override
    public Company create(CreateCompanyCommand command) {
        if (null != baseUserService.searchByPhone(command.getPhone())) {
            throw new ExistException("用户名[" + command.getPhone() + "]已存在");
        }

        String salt = PasswordHelper.getSalt();
        String password = PasswordHelper.encryptPassword(command.getPassword(), command.getPhone() + salt);

        Role role = roleService.searchByName("company");

        Area registerAddress = areaService.show(command.getRegisterAddress());
        Area operateAddress = areaService.show(command.getOperateAddress());

        Company company = new Company(command.getPhone(), password, salt, command.getStatus(),
                new BigDecimal(0), new Date(), role, command.getEmail(), 2, command.getName(), command.getFolder(),
                command.getRegisterDate(), registerAddress, operateAddress, new BigDecimal(0), 0.0);

        companyRepository.save(company);
        return company;
    }

    @Override
    public Company edit(EditCompanyCommand command) {
        Company company = this.show(command.getId());
        company.fainWhenConcurrencyViolation(command.getVersion());

        Area registerAddress = areaService.show(command.getRegisterAddress());
        Area operateAddress = areaService.show(command.getOperateAddress());

        company.setEmail(command.getEmail());
        company.setName(command.getName());
        company.setFolder(command.getFolder());
        command.setRegisterDate(command.getRegisterDate());
        company.setRegisterAddress(registerAddress);
        company.setOperateAddress(operateAddress);

        companyRepository.update(company);

        return company;
    }

    @Override
    public Company show(String id) {
        Company company = companyRepository.getById(id);
        if (null == company) {
            throw new NoFoundException("没有找到用户id=[" + id + "]的记录");
        }
        return null;
    }
}