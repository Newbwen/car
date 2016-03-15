package pengyi.application.user.company;

import pengyi.application.user.company.command.EditCompanyCommand;
import pengyi.application.user.company.command.BaseListCompanyCommand;
import pengyi.application.user.company.representation.CompanyRepresentation;
import pengyi.repository.generic.Pagination;

/**
 * Created by YJH on 2016/3/7.
 */
public interface ICompanyAppService {

    Pagination<CompanyRepresentation> pagination(BaseListCompanyCommand command);

    CompanyRepresentation edit(EditCompanyCommand command);

    CompanyRepresentation show(String id);
}
