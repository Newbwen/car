package pengyi.domain.service.rescue;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pengyi.application.rescue.command.CreateRescueCommand;
import pengyi.application.rescue.command.EditRescueCommand;
import pengyi.application.rescue.command.ListRescueCommand;
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

import java.util.*;


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
            criteriaList.add(Restrictions.like("a.userName", command.getApplyUser(), MatchMode.ANYWHERE));
        }
        if (!CoreStringUtils.isEmpty(command.getDriver())) {
            criteriaList.add(Restrictions.like("d.userName", command.getDriver(), MatchMode.ANYWHERE));
        }
        if (null != command.getStatus()) {
            criteriaList.add(Restrictions.eq("status", command.getStatus()));

        }
        List<Order> orderList = new ArrayList<Order>();
        orderList.add(Order.desc("applyTime"));
        Map<String, String> aliasMap = new HashMap<String, String>();
        aliasMap.put("applyUser", "a");
        aliasMap.put("driver", "d");

        return rescueRepository.pagination(command.getPage(), command.getPageSize(), criteriaList, aliasMap, orderList, null, null);
    }

    @Override
    public Rescue create(CreateRescueCommand command) {

        BaseUser applyuser = baseUserService.show(command.getApplyUser());
        Driver driver = driverService.show(command.getDriver());
        Rescue rescue1 = new Rescue(applyuser, new Date(), command.getType(), command.getDescription(), driver, null, RescueStatus.WAIT_RESCUE, null);
        rescueRepository.save(rescue1);

        return rescue1;
    }

    @Override
    public Rescue edit(EditRescueCommand command) {
        Rescue rescue=this.show(command.getId());
        BaseUser applyUser = baseUserService.show(command.getApplyUser());
        Driver driver = driverService.show(command.getDriver());
        rescue.setApplyUser(applyUser);
        rescue.setStatus(command.getStatus());
        rescue.setType(command.getType());
        rescue.setDescription(command.getDescription());
        rescue.setDriver(driver);
        rescueRepository.save(rescue);

        return rescue;
    }

    @Override
    public Rescue show(String id) {
        Rescue rescue = (Rescue) rescueRepository.getById(id);
        if (null == rescue) {
            throw new NoFoundException("没有找到救援id=[" + id + "]的记录");
        }
        return rescue;
    }

    @Override
    public Rescue updateStatus(EditRescueCommand command) {
        Rescue rescue = this.show(command.getId());
        rescue.fainWhenConcurrencyViolation(command.getVersion());
        if (rescue.getStatus().equals(RescueStatus.WAIT_RESCUE)) {
            rescue.setStatus(RescueStatus.IN_RESCUE);
        } else if (rescue.getStatus().equals(RescueStatus.IN_RESCUE)) {
            rescue.setStatus(RescueStatus.SUCCESS_RESCUE);
        } else if (rescue.getStatus().equals(RescueStatus.SUCCESS_RESCUE)) {
            rescue.setStatus(RescueStatus.WAIT_RESCUE);
        }
        rescueRepository.update(rescue);
        return rescue;
    }

    @Override
    public Rescue searchByName(String rescueName) {
        return rescueRepository.getByName(rescueName);
    }

    @Override
    public Rescue apiUpdateRescue(EditRescueCommand command) {

        Rescue rescue = this.show(command.getId());
        Driver driver = driverService.show(command.getDriver());
        rescue.setStatus(RescueStatus.IN_RESCUE);
        rescue.setDriver(driver);
        rescueRepository.update(rescue);

        return rescue;
    }

    @Override
    public Rescue apiCancelRescue(EditRescueCommand command) {

        Rescue rescue = this.show(command.getId());
        rescue.fainWhenConcurrencyViolation(command.getVersion());
            rescue.setStatus(RescueStatus.CANCEL_RESCUE);
            rescueRepository.update(rescue);
        return rescue;
    }

    @Override
    public Rescue apifinishRescue(EditRescueCommand command) {

        Rescue rescue=this.show(command.getId());
        rescue.fainWhenConcurrencyViolation(command.getVersion());
        rescue.setStatus(RescueStatus.CANCEL_RESCUE);
        rescueRepository.update(rescue);
        return rescue;
    }


}
