package pengyi.application.user.company;

import pengyi.application.user.company.command.CreateCompanyCommand;
import pengyi.application.user.company.command.EditCompanyCommand;
import pengyi.application.user.company.representation.CompanyRepresentation;

/**
 * Created by YJH on 2016/3/7.
 */
public interface ICompanyAppService {

    CompanyRepresentation create(CreateCompanyCommand command);

    CompanyRepresentation edit(EditCompanyCommand command);

    CompanyRepresentation show(String id);

}
