package pengyi.application.user;

import pengyi.application.user.command.LoginUserCommand;
import pengyi.application.user.command.ResetPasswordCommand;
import pengyi.application.user.representation.BaseUserRepresentation;
import pengyi.core.api.BaseResponse;
import pengyi.domain.model.user.BaseUser;

/**
 * Created by YJH on 2016/3/15.
 */
public interface IApiBaseUserAppService {
    BaseUserRepresentation apiSearchByUserName(String userName);

    BaseUser login(LoginUserCommand command);

    BaseResponse resetPassword(ResetPasswordCommand command);
}
