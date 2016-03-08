package pengyi.domain.service.user.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pengyi.application.user.user.command.CreateUserCommand;
import pengyi.application.user.user.command.EditUserCommand;
import pengyi.core.commons.PasswordHelper;
import pengyi.core.exception.ExistException;
import pengyi.core.exception.NoFoundException;
import pengyi.domain.model.role.Role;
import pengyi.domain.model.user.BaseUser;
import pengyi.domain.model.user.user.IUserRepository;
import pengyi.domain.model.user.user.User;
import pengyi.domain.service.role.IRoleService;
import pengyi.domain.service.user.BaseUserService;
import pengyi.domain.service.user.IBaseUserService;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by YJH on 2016/3/7.
 */
@Service("userService")
public class UserService implements IUserService {

    @Autowired
    private IUserRepository<User, String> userRepository;

    @Autowired
    private IBaseUserService baseUserService;

    @Autowired
    private IRoleService roleService;

    @Override
    public User create(CreateUserCommand command) {

        if (null != baseUserService.searchByPhone(command.getPhone())) {
            throw new ExistException("用户名[" + command.getPhone() + "]已存在");
        }

        String salt = PasswordHelper.getSalt();
        String password = PasswordHelper.encryptPassword(command.getPassword(), command.getPhone() + salt);

        Role role = roleService.searchByName("user");

        User user = new User(command.getPhone(), password, salt, command.getStatus(),
                new BigDecimal(0), new Date(), role, command.getEmail(), 2, command.getName(),
                command.getHead(), command.getSex(), 0, new BigDecimal(0), 0);

        userRepository.save(user);

        return user;
    }

    @Override
    public User edit(EditUserCommand command) {
        User user = this.show(command.getId());
        user.fainWhenConcurrencyViolation(command.getVersion());

        user.setEmail(command.getEmail());
        user.setHead(command.getHead());
        user.setSex(command.getSex());
        user.setName(command.getName());

        userRepository.update(user);
        return user;
    }

    @Override
    public User show(String id) {
        User user = userRepository.getById(id);
        if (null == user) {
            throw new NoFoundException("没有找到用户id=[" + id + "]的记录");
        }
        return user;
    }
}
