package pengyi.domain.service.user;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pengyi.application.user.command.BaseCreateBaseUserCommand;
import pengyi.application.user.command.EditBaseUserRoleCommand;
import pengyi.application.user.command.ListBaseUserCommand;
import pengyi.application.user.command.UpDatePasswordCommand;
import pengyi.core.commons.PasswordHelper;
import pengyi.core.commons.command.EditStatusCommand;
import pengyi.core.exception.ExistException;
import pengyi.core.exception.NoFoundException;
import pengyi.core.exception.NotEqualException;
import pengyi.core.type.EnableStatus;
import pengyi.core.util.CoreStringUtils;
import pengyi.domain.model.role.Role;
import pengyi.domain.model.user.BaseUser;
import pengyi.domain.model.user.IBaseUserRepository;
import pengyi.domain.service.role.IRoleService;
import pengyi.repository.generic.Pagination;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by YJH on 2016/3/7.
 */
@Service("baseUserService")
public class BaseUserService implements IBaseUserService {

    @Autowired
    private IBaseUserRepository<BaseUser, String> baseUserRepository;

    @Autowired
    private IRoleService roleService;

    @Override
    public BaseUser searchByPhone(String phone) {
        return baseUserRepository.getByPhone(phone);
    }

    @Override
    public Pagination<BaseUser> pagination(ListBaseUserCommand command) {
        List<Criterion> criteriaList = new ArrayList();
        if (!CoreStringUtils.isEmpty(command.getPhone())) {
            criteriaList.add(Restrictions.like("phone", command.getPhone(), MatchMode.ANYWHERE));
        }

        if (null != command.getStatus()) {
            criteriaList.add(Restrictions.eq("status", command.getStatus()));
        }
        return baseUserRepository.pagination(command.getPage(), command.getPageSize(), criteriaList, null);
    }

    @Override
    public BaseUser updatePassword(UpDatePasswordCommand command) {
        BaseUser baseUser = this.show(command.getId());

        baseUser.fainWhenConcurrencyViolation(command.getVersion());

        if (!PasswordHelper.equalsPassword(baseUser, command.getOldPassword())) {
            throw new NotEqualException("旧密码错误");
        }

        String newPassword = PasswordHelper.encryptPassword(command.getNewPassword(), baseUser.getCredentialsSalt());

        baseUser.setPassword(newPassword);

        baseUserRepository.update(baseUser);
        return baseUser;
    }

    public BaseUser show(String id) {
        BaseUser baseUser = baseUserRepository.getById(id);
        if (null == baseUser) {
            throw new NoFoundException("没有找到用户id=[" + id + "]的记录");
        }
        return baseUser;
    }

    @Override
    public BaseUser updateStatus(EditStatusCommand command) {
        BaseUser baseUser = this.show(command.getId());
        baseUser.fainWhenConcurrencyViolation(command.getVersion());

        if (baseUser.getStatus().equals("ENABLE")) {
            baseUser.setStatus(EnableStatus.DISABLE);
        } else {
            baseUser.setStatus(EnableStatus.ENABLE);
        }
        return baseUser;
    }

    @Override
    public BaseUser updateBaseUserRole(EditBaseUserRoleCommand command) {
        BaseUser baseUser = this.show(command.getId());
        baseUser.fainWhenConcurrencyViolation(command.getVersion());

        Role role = roleService.show(command.getUserRole());

        baseUser.setUserRole(role);

        baseUserRepository.update(baseUser);
        return baseUser;
    }
}
