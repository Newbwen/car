package pengyi.domain.service.user.company;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pengyi.application.user.company.command.EditCompanyCommand;
import pengyi.application.user.company.command.BaseListCompanyCommand;
import pengyi.application.user.company.command.UpdateFolderCommand;
import pengyi.core.commons.PasswordHelper;
import pengyi.core.exception.ExistException;
import pengyi.core.exception.NoFoundException;
import pengyi.core.type.UserType;
import pengyi.core.util.CoreStringUtils;
import pengyi.domain.model.area.Area;
import pengyi.domain.model.role.Role;
import pengyi.domain.model.user.BaseUser;
import pengyi.domain.model.user.company.Company;
import pengyi.domain.model.user.company.ICompanyRepository;
import pengyi.domain.service.area.IAreaService;
import pengyi.domain.service.role.IRoleService;
import pengyi.domain.service.user.IBaseUserService;
import pengyi.repository.generic.Pagination;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by YJH on 2016/3/7.
 */
@Service("companyService")
public class CompanyService implements ICompanyService {

    @Autowired
    private ICompanyRepository<Company, String> companyRepository;

    @Autowired
    private IAreaService areaService;

    @Override
    public Pagination<Company> pagination(BaseListCompanyCommand command) {
        List<Criterion> criteriaList = new ArrayList();
        if (!CoreStringUtils.isEmpty(command.getUserName())) {
            criteriaList.add(Restrictions.like("userName", command.getUserName(), MatchMode.ANYWHERE));
        }

        if (null != command.getStatus()) {
            criteriaList.add(Restrictions.eq("status", command.getStatus()));
        }
        return companyRepository.pagination(command.getPage(), command.getPageSize(), criteriaList, null);
    }

    @Override
    public Company edit(EditCompanyCommand command) {
        Company company = this.show(command.getId());
        company.fainWhenConcurrencyViolation(command.getVersion());

        Area registerAddress = areaService.show(command.getRegisterAddress());
        Area operateAddress = areaService.show(command.getOperateAddress());

        company.setEmail(command.getEmail());
        company.setName(command.getName());
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
        return company;
    }

    @Override
    public Company create(Company company) {
        companyRepository.save(company);
        return company;
    }

    @Override
    public Company apiEdit(EditCompanyCommand command) {
        Company company = this.show(command.getId());
        company.fainWhenConcurrencyViolation(command.getVersion());

        Area registerAddress = areaService.show(command.getRegisterAddress());
        Area operateAddress = areaService.show(command.getOperateAddress());

        company.setName(command.getName());
        company.setRegisterAddress(registerAddress);
        company.setOperateAddress(operateAddress);

        companyRepository.update(company);
        return company;
    }

    @Override
    public void apiUpdateFolder(UpdateFolderCommand command) {
        Company company = this.show(command.getId());

        company.setFolder(command.getFolder());

        companyRepository.update(company);
    }
}
