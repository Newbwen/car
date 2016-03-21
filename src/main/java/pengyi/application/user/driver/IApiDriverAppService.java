package pengyi.application.user.driver;

import pengyi.application.user.driver.command.CompanyAuditingDriverCommand;
import pengyi.application.user.driver.command.CompanyDriverEditCommand;
import pengyi.application.user.driver.command.CompanyDriverListCommand;
import pengyi.application.user.driver.command.CreateDriverCommand;
import pengyi.core.api.BaseResponse;
import pengyi.core.commons.command.EditStatusCommand;

/**
 * Created by YJH on 2016/3/15.
 */
public interface IApiDriverAppService {
    BaseResponse companyDriverList(CompanyDriverListCommand command);

    BaseResponse companyEditDriver(CompanyDriverEditCommand command);

    BaseResponse companyAuditingDriver(CompanyAuditingDriverCommand command);

    BaseResponse companyEditStatusDriver(EditStatusCommand command);

    BaseResponse companyCreateDriver(CreateDriverCommand command);

    BaseResponse companyExpelDriver(EditStatusCommand command);
}
