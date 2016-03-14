package pengyi.domain.service.user.user;

import pengyi.application.user.user.command.BaseListUserCommand;
import pengyi.application.user.user.command.EditUserCommand;
import pengyi.domain.model.user.user.User;
import pengyi.repository.generic.Pagination;

/**
 * Created by YJH on 2016/3/7.
 */
public interface IUserService {

    Pagination<User> pagination(BaseListUserCommand command);

    User edit(EditUserCommand command);

    User show(String id);

    User create(User user);
}
