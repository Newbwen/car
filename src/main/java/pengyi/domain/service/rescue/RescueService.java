package pengyi.domain.service.rescue;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pengyi.application.rescue.command.CreateRescueCommand;
import pengyi.application.rescue.command.EditRescueCommand;
import pengyi.application.rescue.command.ListRescueCommand;
import pengyi.core.exception.ExistException;
import pengyi.core.exception.NoFoundException;
import pengyi.core.type.RescueStatus;
import pengyi.core.util.CoreStringUtils;
import pengyi.domain.model.rescue.IRescueRepository;
import pengyi.domain.model.rescue.Rescue;
import pengyi.domain.model.user.BaseUser;
import pengyi.domain.model.user.driver.Driver;
import pengyi.domain.service.user.BaseUserService;
import pengyi.domain.service.user.driver.DriverService;
import pengyi.repository.generic.Pagination;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Created by lvdi on 2015/3/8.
 */

@Service("rescueService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class RescueService implements IRescueService {

    @Autowired
    private IRescueRepository rescueRepository;

    @Autowired
    private BaseUserService baseUserService;

    @Autowired
    private DriverService driverService;

    @Override
    public Rescue getById(int rescueId) {
        return (Rescue) rescueRepository.getById(rescueId);
    }

    @Override
    public Pagination<Rescue> pagination(ListRescueCommand command) {

        List<Criterion> criteriaList = new ArrayList();
        if (!CoreStringUtils.isEmpty(command.getApplyUser())) {
            criteriaList.add(Restrictions.eq("applyUser", command.getApplyUser()));
        }
        if (!CoreStringUtils.isEmpty(command.getApplyUser())) {
            criteriaList.add(Restrictions.eq("driver", command.getDriver()));
        }
        return rescueRepository.pagination(command.getPage(), command.getPageSize(), criteriaList, null);
    }

    @Override
    public Rescue create(CreateRescueCommand command) {
      if(null!=command.getApplyUser()){
          throw new ExistException("申请救援人[" + command.getApplyUser() + "]的记录已存在");
      }
      if(null!=command.getDriver()){
          throw new ExistException("申请司机[" + command.getApplyUser() + "]的记录已存在");

      }
        BaseUser applyuser=baseUserService.show(command.getApplyUser());
        Driver driver=driverService.show(command.getDriver());
        Rescue rescue1=new Rescue(applyuser,new Date(),command.getType(),command.getDescription(),driver,new Date(),command.getStatus(),new Date());
        rescueRepository.save(rescue1);
        return rescue1;
    }

    @Override
    public Rescue edit(EditRescueCommand command) {

        Rescue rescue=this.show(command.getApplyUser());
        if(null!=this.searchByName(command.getApplyUser())){
            throw new ExistException("申请人[" + command.getApplyUser() + "]的记录已存在");
        }
        BaseUser applyuser=baseUserService.show(command.getApplyUser());
        Driver driver=driverService.show(command.getDriver());
        Rescue rescue1=new Rescue(applyuser,new Date(),command.getType(),command.getDescription(),driver,new Date(),command.getStatus(),new Date());
        rescueRepository.save(rescue1);
        return rescue1;
    }

    @Override
    public Rescue show(String id) {
        Rescue rescue= (Rescue) rescueRepository.getById(id);
        if (null == rescue) {
            throw new NoFoundException("没有找到救援id=[" + id + "]的记录");
        }
        return rescue;
    }

    @Override
    public Rescue updateStatus(EditRescueCommand command) {
        Rescue rescue=this.show(command.getId());
        rescue.fainWhenConcurrencyViolation(command.getVersion());
        if (rescue.getStatus().equals("WAIT_RESCUE")) {
            rescue.setStatus(RescueStatus.WAIT_RESCUE);
        } else if(rescue.getStatus().equals("IN_RESCUE")){
            rescue.setStatus(RescueStatus.WAIT_RESCUE);
        }else if(rescue.getStatus().equals("SUCCESS_RESCUE")){
            rescue.setStatus(RescueStatus.WAIT_RESCUE);
        }
        rescueRepository.update(rescue);
        return rescue;
    }

    @Override
    public Rescue searchByName(String rescueName) {
        return rescueRepository.getByName(rescueName);
    }
}
