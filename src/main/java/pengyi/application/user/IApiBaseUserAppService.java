package pengyi.application.user;

import pengyi.application.user.representation.BaseUserRepresentation;

/**
 * Created by YJH on 2016/3/15.
 */
public interface IApiBaseUserAppService {
    BaseUserRepresentation apiSearchByUserName(String userName);
}
