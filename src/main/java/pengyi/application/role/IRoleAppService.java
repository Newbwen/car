package pengyi.application.role;

import pengyi.application.role.command.CreateRoleCommand;
import pengyi.application.role.command.EditRoleCommand;
import pengyi.application.role.command.ListRoleCommand;
import pengyi.application.role.representation.RoleRepresentation;
import pengyi.core.commons.command.EditStatusCommand;
import pengyi.repository.generic.Pagination;

/**
 * Created by YJH on 2016/3/7.
 */
public interface IRoleAppService {

    Pagination<RoleRepresentation> pagination(ListRoleCommand command);

    RoleRepresentation create(CreateRoleCommand command);

    RoleRepresentation edit(EditRoleCommand command);

    RoleRepresentation show(String id);

    RoleRepresentation updateStatus(EditStatusCommand command);

}
