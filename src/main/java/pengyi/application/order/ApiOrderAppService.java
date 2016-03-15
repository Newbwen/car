package pengyi.application.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pengyi.application.order.command.CompanyOrderListCommand;
import pengyi.application.order.representation.OrderRepresentation;
import pengyi.core.api.BaseResponse;
import pengyi.core.api.ResponseCode;
import pengyi.core.api.ResponseMessage;
import pengyi.core.mapping.IMappingService;
import pengyi.core.util.CoreStringUtils;
import pengyi.domain.model.order.Order;
import pengyi.domain.service.order.IOrderService;
import pengyi.repository.generic.Pagination;

import java.util.List;

/**
 * Created by YJH on 2016/3/15.
 */
@Service("apiOrderAppService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class ApiOrderAppService implements IApiOrderAppService {

    @Autowired
    private IOrderService orderService;

    @Autowired
    private IMappingService mappingService;

    @Override
    public BaseResponse companyOrderList(CompanyOrderListCommand command) {
        if (null != command) {
            if (!CoreStringUtils.isEmpty(command.getCompany())) {
                return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_10006.getMessage());
            }
            Pagination<Order> pagination = orderService.apiPagination(command);
            List<OrderRepresentation> data = mappingService.mapAsList(pagination.getData(), OrderRepresentation.class);
            Pagination<OrderRepresentation> result = new Pagination<OrderRepresentation>(data, pagination.getCount(), pagination.getPage(), pagination.getPageSize());
            return new BaseResponse(ResponseCode.RESPONSE_CODE_SUCCESS, 0, result, ResponseCode.RESPONSE_CODE_SUCCESS.getMessage());
        } else {
            return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseCode.RESPONSE_CODE_PARAMETER_ERROR.getMessage());
        }
    }

}
