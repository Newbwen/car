package pengyi.domain.service.billing;

import pengyi.application.billing.command.CreateBillingCommand;
import pengyi.application.billing.command.EditBillingCommand;
import pengyi.application.billing.command.ListBillingCommand;
import pengyi.domain.model.billing.Billing;
import pengyi.repository.generic.Pagination;

/**
 * Created by YJH on 2016/3/21.
 */
public interface IBillingService {
    Pagination<Billing> pagination(ListBillingCommand command);

    Billing show(String id);

    Billing create(CreateBillingCommand command);

    Billing edit(EditBillingCommand command);

    Billing searchByCompany(String id);
}
