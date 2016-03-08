package pengyi.application.user.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pengyi.application.user.user.command.CreateUserCommand;
import pengyi.application.user.user.command.EditUserCommand;
import pengyi.application.user.user.representation.UserRepresentation;
import pengyi.core.mapping.IMappingService;
import pengyi.domain.service.user.user.IUserService;

/**
 * Created by YJH on 2016/3/7.
 */
@Service("userAppService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class UserAppService implements IUserAppService {

    @Autowired
    private IUserService userService;

    @Autowired
    private IMappingService mappingService;

    @Override
    public UserRepresentation create(CreateUserCommand command) {
        return mappingService.map(userService.create(command), UserRepresentation.class, false);
    }

    @Override
    public UserRepresentation edit(EditUserCommand command) {
        return mappingService.map(userService.edit(command), UserRepresentation.class, false);
    }

    @Override
    public UserRepresentation show(String id) {
        return mappingService.map(userService.show(id), UserRepresentation.class, false);
    }
}
