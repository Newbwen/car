package pengyi.domain.service.user.user;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pengyi.application.user.user.command.EditUserCommand;
import pengyi.application.user.user.command.BaseListUserCommand;
import pengyi.core.commons.PasswordHelper;
import pengyi.core.exception.ExistException;
import pengyi.core.exception.NoFoundException;
import pengyi.core.type.UserType;
import pengyi.core.util.CoreStringUtils;
import pengyi.domain.model.role.Role;
import pengyi.domain.model.user.BaseUser;
import pengyi.domain.model.user.user.IUserRepository;
import pengyi.domain.model.user.user.User;
import pengyi.domain.service.role.IRoleService;
import pengyi.domain.service.user.IBaseUserService;
import pengyi.repository.generic.Pagination;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    public Pagination<User> pagination(BaseListUserCommand command) {
        List<Criterion> criteriaList = new ArrayList();
        if (!CoreStringUtils.isEmpty(command.getUserName())) {
            criteriaList.add(Restrictions.like("userName", command.getUserName(), MatchMode.ANYWHERE));
        }

        if (null != command.getStatus()) {
            criteriaList.add(Restrictions.eq("status", command.getStatus()));
        }
        return userRepository.pagination(command.getPage(), command.getPageSize(), criteriaList, null);
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

    @Override
    public User create(User user) {
        userRepository.save(user);
        return user;
    }

}
