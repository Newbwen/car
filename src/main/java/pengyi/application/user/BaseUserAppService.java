package pengyi.application.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pengyi.application.user.command.EditBaseUserRoleCommand;
import pengyi.application.user.command.ListBaseUserCommand;
import pengyi.application.user.command.UpDatePasswordCommand;
import pengyi.application.user.representation.BaseUserRepresentation;
import pengyi.core.commons.command.EditStatusCommand;
import pengyi.core.mapping.IMappingService;
import pengyi.domain.model.user.BaseUser;
import pengyi.domain.service.user.IBaseUserService;
import pengyi.repository.generic.Pagination;

import java.util.List;

/**
 * Created by YJH on 2016/3/7.
 */
@Service("baseUserAppService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class BaseUserAppService implements IBaseUserAppService {

    @Autowired
    private IBaseUserService baseUserService;

    @Autowired
    private IMappingService mappingService;

    @Override
    @Transactional(readOnly = true)
    public BaseUser searchByPhone(String phone) {
        return baseUserService.searchByPhone(phone);
    }

    @Override
    @Transactional(readOnly = true)
    public Pagination<BaseUserRepresentation> pagination(ListBaseUserCommand command) {
        command.verifyPage();
        command.verifyPageSize(20);
        Pagination<BaseUser> pagination = baseUserService.pagination(command);
        List<BaseUserRepresentation> data = mappingService.mapAsList(pagination.getData(), BaseUserRepresentation.class);
        return new Pagination<BaseUserRepresentation>(data, pagination.getCount(), pagination.getPage(), pagination.getPageSize());
    }

    @Override
    public BaseUserRepresentation updatePassword(UpDatePasswordCommand command) {
        return mappingService.map(baseUserService.updatePassword(command), BaseUserRepresentation.class, false);
    }

    @Override
    public BaseUserRepresentation updateStatus(EditStatusCommand command) {
        return mappingService.map(baseUserService.updateStatus(command), BaseUserRepresentation.class, false);
    }

    @Override
    public BaseUserRepresentation updateBaseUserRole(EditBaseUserRoleCommand command) {
        return mappingService.map(baseUserService.updateBaseUserRole(command), BaseUserRepresentation.class, false);
    }
}
