package pengyi.domain.service.user;

import pengyi.application.user.command.*;
import pengyi.core.commons.command.EditStatusCommand;
import pengyi.domain.model.user.BaseUser;
import pengyi.repository.generic.Pagination;

/**
 * Created by YJH on 2016/3/7.
 */
public interface IBaseUserService {

    BaseUser searchByUserName(String userName);

    Pagination<BaseUser> pagination(BaseListBaseUserCommand command);

    BaseUser updatePassword(UpDatePasswordCommand command);

    BaseUser updateStatus(EditStatusCommand command);

    BaseUser updateBaseUserRole(EditBaseUserRoleCommand command);

    BaseUser show(String id);

    BaseUser create(BaseCreateBaseUserCommand command);

    BaseUser login(LoginUserCommand command);
}
