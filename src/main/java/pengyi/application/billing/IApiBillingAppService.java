package pengyi.application.billing;

import pengyi.application.billing.command.CreateBillingCommand;
import pengyi.application.billing.command.EditBillingCommand;
import pengyi.core.api.BaseResponse;

/**
 * Created by YJH on 2016/3/22.
 */
public interface IApiBillingAppService {
    BaseResponse showByCompany(String id);

    BaseResponse create(CreateBillingCommand command);

    BaseResponse edit(EditBillingCommand command);
}
