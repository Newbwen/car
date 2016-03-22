package pengyi.domain.service.user.company;

import pengyi.application.user.command.UpdatePasswordCommand;
import pengyi.application.user.company.command.BaseListCompanyCommand;
import pengyi.application.user.company.command.CreateCompanyCommand;
import pengyi.application.user.company.command.EditCompanyCommand;
import pengyi.application.user.company.command.UpdateFolderCommand;
import pengyi.domain.model.user.company.Company;
import pengyi.repository.generic.Pagination;

import java.util.List;

/**
 * Created by YJH on 2016/3/7.
 */
public interface ICompanyService {

    Pagination<Company> pagination(BaseListCompanyCommand command);

    Company edit(EditCompanyCommand command);

    Company show(String id);

    Company create(Company company);


    /********** api 方法    *************/
    Company apiEdit(EditCompanyCommand command);

    void apiUpdateFolder(UpdateFolderCommand command);

    Company apiCreate(CreateCompanyCommand command);

    Company apiUpdatePassword(UpdatePasswordCommand command);

    List<Company> apiByName(String name);
}
