package pengyi.application.user.company;

import pengyi.application.user.company.command.EditCompanyCommand;
import pengyi.application.user.company.command.UpdateFolderCommand;
import pengyi.application.user.company.representation.CompanyRepresentation;
import pengyi.core.api.BaseResponse;

/**
 * Created by YJH on 2016/3/15.
 */
public interface IApiCompanyAppService {
    CompanyRepresentation info(String id);

    BaseResponse edit(EditCompanyCommand command);

    BaseResponse updateFolder(UpdateFolderCommand command);
}
