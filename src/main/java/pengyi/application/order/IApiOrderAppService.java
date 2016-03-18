package pengyi.application.order;

import pengyi.application.order.command.CompanyOrderListCommand;
import pengyi.core.api.BaseResponse;

/**
 * Created by YJH on 2016/3/15.
 */
public interface IApiOrderAppService {

    BaseResponse companyOrderPagination(CompanyOrderListCommand command);
}
