package pengyi.application.user.user;

import pengyi.application.user.user.command.CreateUserCommand;
import pengyi.application.user.user.command.EditUserCommand;
import pengyi.application.user.user.representation.UserRepresentation;

/**
 * Created by YJH on 2016/3/7.
 */
public interface IUserAppService {

    UserRepresentation create(CreateUserCommand command);

    UserRepresentation edit(EditUserCommand command);

    UserRepresentation show(String id);

}
