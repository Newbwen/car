package pengyi.domain.service.rescue;

import org.gjt.mm.mysql.Driver;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pengyi.domain.model.rescue.IRescueRepository;
import pengyi.domain.model.rescue.Rescue;
import pengyi.domain.model.user.BaseUser;
import pengyi.repository.generic.Pagination;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lvdi on 2015/3/8.
 */

@Service("rescueService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class RescueService implements IRescueService {

    @Autowired
    private IRescueRepository rescueRepository;

    /**
     * 救援保存
     */
    @Override
    @SuppressWarnings("unchecked")
    public void save(Rescue rescue) {

        Rescue rescue1 = new Rescue();

        rescue1.setApplyUser(rescue.getApplyUser());
        rescue1.setApplyTime(rescue.getApplyTime());
        rescue1.setType(rescue.getType());
        rescue1.setDescription(rescue.getDescription());
        rescue1.setDriver(rescue.getDriver());
        rescue1.setRescueTime(rescue.getRescueTime());
        rescue1.setStatus(rescue.getStatus());
        rescue1.setFinishTime(rescue.getFinishTime());

        rescueRepository.save(rescue1);
    }

    /**
     * 根据救援Id删除救援
     */
    @Override
    @SuppressWarnings("unchecked")
    public void delete(int rescueId) {

        Rescue rescue = getById(rescueId);
        rescueRepository.delete(rescue);

    }


    /**
     * 更新救援
     */
    @Override
    @SuppressWarnings("unchecked")
    public void upadte(Rescue rescue) {

        Rescue rescue1 = new Rescue();

        rescue1.setApplyUser(rescue.getApplyUser());
        rescue1.setApplyTime(rescue.getApplyTime());
        rescue1.setType(rescue.getType());
        rescue1.setDescription(rescue.getDescription());
        rescue1.setDriver(rescue.getDriver());
        rescue1.setRescueTime(rescue.getRescueTime());
        rescue1.setStatus(rescue.getStatus());
        rescue1.setFinishTime(rescue.getFinishTime());

        rescueRepository.update(rescue1);


    }

    /**
     * 根据请求救援人查询救援
     */
    @Override
    @SuppressWarnings("unchecked")
    public Pagination<Rescue> getRescueList(BaseUser applyUser, int page, int pageSize) {

        List<Order> orderList = new ArrayList<Order>();
        orderList.add(Order.desc("createDate"));

        List<Criterion> criterionList = new ArrayList<Criterion>();
        criterionList.add(Restrictions.eq("applyUser", applyUser));

        return rescueRepository.pagination(page, pageSize, (Criterion[]) criterionList.toArray(), (Order[]) orderList.toArray());
    }

    /**
     * 根据接受救援司机查询救援
     */
    @Override
    @SuppressWarnings("unchecked")
    public Pagination<Rescue> getRescueList(Driver driver, int page, int pageSize) {

        List<Order> orderList = new ArrayList<Order>();
        orderList.add(Order.desc("createDate"));

        List<Criterion> criterionList = new ArrayList<Criterion>();
        criterionList.add(Restrictions.eq("driver", driver));

        return rescueRepository.pagination(page, pageSize, (Criterion[]) criterionList.toArray(), (Order[]) orderList.toArray());
    }

    /**
     * 根据救援Id查询救援
     */
    @Override
    @SuppressWarnings("unchecked")
    public Rescue getById(int rescueId) {

        return (Rescue) rescueRepository.getById(rescueId);

    }
}
