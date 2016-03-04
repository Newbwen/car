package pengyi.domain.service.user;

import pengyi.domain.model.user.BaseUser;

import java.util.List;

/**
 * Created by pengyi on 2015/12/24.
 */
public interface IUserService {

    void save(String userId);

    BaseUser findByUserId(String userId);

    List<BaseUser> getUserList();

}
