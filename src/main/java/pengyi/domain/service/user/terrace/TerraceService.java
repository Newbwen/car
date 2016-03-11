package pengyi.domain.service.user.terrace;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pengyi.application.user.terrace.command.CreateTerraceCommand;
import pengyi.application.user.terrace.command.EditTerraceCommand;
import pengyi.application.user.terrace.command.BaseListTerraceCommand;
import pengyi.core.commons.PasswordHelper;
import pengyi.core.exception.ExistException;
import pengyi.core.exception.NoFoundException;
import pengyi.core.type.UserType;
import pengyi.core.util.CoreStringUtils;
import pengyi.domain.model.role.Role;
import pengyi.domain.model.user.terrace.ITerraceRepository;
import pengyi.domain.model.user.terrace.Terrace;
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
@Service("terraceService")
public class TerraceService implements ITerraceService {

    @Autowired
    private ITerraceRepository<Terrace, String> terraceRepository;

    @Autowired
    private IBaseUserService baseUserService;

    @Autowired
    private IRoleService roleService;

    @Override
    public Pagination<Terrace> pagination(BaseListTerraceCommand command) {
        List<Criterion> criteriaList = new ArrayList();
        if (!CoreStringUtils.isEmpty(command.getUserName())) {
            criteriaList.add(Restrictions.like("userName", command.getUserName(), MatchMode.ANYWHERE));
        }

        if (null != command.getStatus()) {
            criteriaList.add(Restrictions.eq("status", command.getStatus()));
        }
        return terraceRepository.pagination(command.getPage(), command.getPageSize(), criteriaList, null);
    }

    @Override
    public Terrace create(CreateTerraceCommand command) {
        if(null != baseUserService.searchByUserName(command.getUserName())){
            throw new ExistException("用户名[" + command.getUserName() + "]已存在");
        }

        String salt = PasswordHelper.getSalt();
        String password = PasswordHelper.encryptPassword(command.getPassword(), command.getUserName() + salt);

        Role role = roleService.searchByName("terrace");

        Terrace terrace = new Terrace(command.getUserName(), password, salt, command.getStatus(),
                new BigDecimal(0), new Date(), role, command.getEmail(), UserType.TERRACR,command.getName());

        terraceRepository.save(terrace);

        return terrace;
    }

    @Override
    public Terrace edit(EditTerraceCommand command) {
        Terrace terrace = this.show(command.getId());
        terrace.fainWhenConcurrencyViolation(command.getVersion());

        terrace.setEmail(command.getEmail());
        terrace.setName(command.getName());

        terraceRepository.update(terrace);
        return terrace;
    }

    @Override
    public Terrace show(String id) {
        Terrace terrace = terraceRepository.getById(id);
        if(null == terrace){
            throw new NoFoundException("没有找到用户id=[" + id + "]的记录");
        }
        return terrace;
    }
}
