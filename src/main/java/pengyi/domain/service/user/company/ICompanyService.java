package pengyi.domain.service.user.company;

import pengyi.application.user.company.command.CreateCompanyCommand;
import pengyi.application.user.company.command.EditCompanyCommand;
import pengyi.domain.model.user.company.Company;

/**
 * Created by YJH on 2016/3/7.
 */
public interface ICompanyService {

    Company create(CreateCompanyCommand command);

    Company edit(EditCompanyCommand command);

    Company show(String id);

}
