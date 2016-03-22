package pengyi.domain.service.billing;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pengyi.application.billing.command.CreateBillingCommand;
import pengyi.application.billing.command.EditBillingCommand;
import pengyi.application.billing.command.ListBillingCommand;
import pengyi.core.exception.NoFoundException;
import pengyi.domain.model.area.Area;
import pengyi.domain.model.billing.Billing;
import pengyi.domain.model.billing.IBillingRepository;
import pengyi.domain.service.area.IAreaService;
import pengyi.repository.generic.Pagination;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YJH on 2016/3/21.
 */
@Service("billingService")
public class BillingService implements IBillingService {

    @Autowired
    private IBillingRepository<Billing, String> billingRepository;

    @Autowired
    private IAreaService areaService;

    @Override
    public Pagination<Billing> pagination(ListBillingCommand command) {
        List<Criterion> criterionList = new ArrayList<Criterion>();


        List<Order> orderList = new ArrayList<Order>();

        return billingRepository.pagination(command.getPage(), command.getPageSize(), criterionList, orderList);
    }

    @Override
    public Billing show(String id) {
        Billing billing = billingRepository.getById(id);
        if (null == billing) {
            throw new NoFoundException("没有找到计费模板id=[" + id + "]的记录");
        }
        return billing;
    }

    @Override
    public Billing create(CreateBillingCommand command) {
        Area area = areaService.show(command.getArea());

        Billing billing = new Billing(command.getKMBilling(), command.getMinuteBilling(), area);

        billingRepository.save(billing);

        return billing;
    }

    @Override
    public Billing edit(EditBillingCommand command) {
        Billing billing = this.show(command.getId());

        Area area = areaService.show(command.getArea());

        billing.setKmBilling(command.getKMBilling());
        billing.setMinuteBilling(command.getMinuteBilling());
        billing.setArea(area);

        billingRepository.update(billing);
        return billing;
    }

}
