package pengyi.domain.service.user.user;

import pengyi.application.user.user.command.CreateUserCommand;
import pengyi.application.user.user.command.EditUserCommand;
import pengyi.domain.model.user.user.User;

/**
 * Created by YJH on 2016/3/7.
 */
public interface IUserService {

    User create(CreateUserCommand command);

    User edit(EditUserCommand command);

    User show(String id);
}
