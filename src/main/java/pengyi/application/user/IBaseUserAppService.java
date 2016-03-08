package pengyi.application.user;

import pengyi.application.user.command.EditBaseUserRoleCommand;
import pengyi.application.user.command.ListBaseUserCommand;
import pengyi.application.user.command.UpDatePasswordCommand;
import pengyi.application.user.representation.BaseUserRepresentation;
import pengyi.core.commons.command.EditStatusCommand;
import pengyi.domain.model.user.BaseUser;
import pengyi.repository.generic.Pagination;

/**
 * Created by YJH on 2016/3/7.
 */
public interface IBaseUserAppService {
    BaseUser searchByPhone(String phone);

    Pagination<BaseUserRepresentation> pagination(ListBaseUserCommand command);

    BaseUserRepresentation updatePassword(UpDatePasswordCommand command);

    BaseUserRepresentation updateStatus(EditStatusCommand command);

    BaseUserRepresentation updateBaseUserRole(EditBaseUserRoleCommand command);
}