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
    public List<Rescue> findAllRescue() {
        return rescueRepository.findAll();
    }

    @Override
    public Rescue getById(int rescueId) {
        return (Rescue) rescueRepository.getById(rescueId);
    }

    @Override
    public Pagination<Rescue> pagination(ListRescueCommand command) {

        List<Criterion> criteriaList = new ArrayList();
        if (!CoreStringUtils.isEmpty(command.getApplyUser())) {
            criteriaList.add(Restrictions.eq("applyuser.id", command.getApplyUser()));
        }
        if (!CoreStringUtils.isEmpty(command.getApplyUser())) {
            criteriaList.add(Restrictions.eq("driver.id", command.getDriver()));
        }
        if(null != command.getStatus()){
            criteriaList.add(Restrictions.eq("status", command.getStatus()));

        }
        return rescueRepository.pagination(command.getPage(), command.getPageSize(), criteriaList, null);
    }

    @Override
    public Rescue create(CreateRescueCommand command) {

        BaseUser applyuser=baseUserService.show(command.getApplyUser());
        Driver driver=driverService.show(command.getDriver());
        Rescue rescue1=new Rescue(applyuser,new Date(),command.getType(),command.getDescription(),driver,new Date(),command.getStatus(),new Date());
        rescueRepository.save(rescue1);

        return rescue1;
    }

    @Override
    public Rescue edit(EditRescueCommand command) {

        Rescue rescue=this.show(command.getApplyUser());
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
