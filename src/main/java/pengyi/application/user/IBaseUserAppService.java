package pengyi.application.user;

import pengyi.application.user.command.BaseCreateBaseUserCommand;
import pengyi.application.user.command.BaseListBaseUserCommand;
import pengyi.application.user.command.EditBaseUserRoleCommand;
import pengyi.application.user.command.UpDatePasswordCommand;
import pengyi.application.user.representation.BaseUserRepresentation;
import pengyi.core.commons.command.EditStatusCommand;
import pengyi.domain.model.user.BaseUser;
import pengyi.repository.generic.Pagination;

/**
 * Created by YJH on 2016/3/7.
 */
public interface IBaseUserAppService {
    BaseUser searchByUserName(String userName);

    Pagination<BaseUserRepresentation> pagination(BaseListBaseUserCommand command);

    BaseUserRepresentation updatePassword(UpDatePasswordCommand command);

    BaseUserRepresentation updateStatus(EditStatusCommand command);

    BaseUserRepresentation updateBaseUserRole(EditBaseUserRoleCommand command);

    BaseUserRepresentation create(BaseCreateBaseUserCommand command);
}
