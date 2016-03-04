package pengyi.domain.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pengyi.domain.model.user.BaseUser;
import pengyi.domain.model.user.IUserRepository;

import java.util.List;

/**
 * Created by pengyi on 2015/12/24.
 */
@Service("userService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class UserService implements IUserService {

    @Autowired
    private IUserRepository userRepository;

    @Override
    @SuppressWarnings("unchecked")
    public void save(String userId) {
        BaseUser baseUser = new BaseUser();
        baseUser.setId(userId);
        userRepository.save(baseUser);
    }

    @Override
    @Transactional(readOnly = true)
    @SuppressWarnings("unchecked")
    public BaseUser findByUserId(String userId) {
        return (BaseUser) userRepository.getById(userId);
    }

    @Override
    @Transactional(readOnly = true)
    @SuppressWarnings("unchecked")
    public List<BaseUser> getUserList() {
        return userRepository.findAll();
    }
}
