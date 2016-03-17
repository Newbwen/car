package pengyi.application.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pengyi.application.user.representation.BaseUserRepresentation;
import pengyi.core.mapping.IMappingService;
import pengyi.domain.model.user.BaseUser;
import pengyi.domain.service.user.IBaseUserService;

/**
 * Created by YJH on 2016/3/15.
 */
@Service("apiBaseUserAppService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class ApiBaseUserAppService implements IApiBaseUserAppService {

    @Autowired
    private IBaseUserService baseUserService;

    @Autowired
    private IMappingService mappingService;

    @Override
    @Transactional(readOnly = true)
    public BaseUserRepresentation apiSearchByUserName(String userName) {
        BaseUser baseUser = baseUserService.searchByUserName(userName);
        if (null != baseUser) {
            return mappingService.map(baseUser, BaseUserRepresentation.class, false);
        }
        return null;
    }
}
