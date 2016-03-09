package pengyi.application.user.company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pengyi.application.user.company.command.CreateCompanyCommand;
import pengyi.application.user.company.command.EditCompanyCommand;
import pengyi.application.user.company.representation.CompanyRepresentation;
import pengyi.core.mapping.IMappingService;
import pengyi.domain.service.user.company.ICompanyService;

/**
 * Created by YJH on 2016/3/7.
 */
@Service("companyAppService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class CompanyAppService implements ICompanyAppService {

    @Autowired
    private ICompanyService companyService;

    @Autowired
    private IMappingService mappingService;

    @Override
    public CompanyRepresentation create(CreateCompanyCommand command) {
        return mappingService.map(companyService.create(command), CompanyRepresentation.class, false);
    }

    @Override
    public CompanyRepresentation edit(EditCompanyCommand command) {
        return mappingService.map(companyService.edit(command), CompanyRepresentation.class, false);
    }

    @Override
    public CompanyRepresentation show(String id) {
        return mappingService.map(companyService.show(id), CompanyRepresentation.class, false);
    }
}
