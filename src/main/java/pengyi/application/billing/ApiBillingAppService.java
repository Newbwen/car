package pengyi.application.billing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pengyi.application.billing.representation.BillingRepresentation;
import pengyi.core.api.BaseResponse;
import pengyi.core.api.ResponseCode;
import pengyi.core.mapping.IMappingService;
import pengyi.core.util.CoreStringUtils;
import pengyi.domain.model.billing.Billing;
import pengyi.domain.service.billing.IBillingService;

/**
 * Created by YJH on 2016/3/22.
 */
@Service("apiBillingAppService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class ApiBillingAppService implements IApiBillingAppService {

    @Autowired
    private IBillingService billingService;

    @Autowired
    private IMappingService mappingService;

    @Override
    public BaseResponse searchByArea(String driverId) {
        if (!CoreStringUtils.isEmpty(driverId)) {
            Billing data = billingService.apiSearchByArea(driverId);
            if (null == data) {
                BillingRepresentation billing = mappingService.map(data, BillingRepresentation.class, false);
                return new BaseResponse(ResponseCode.RESPONSE_CODE_SUCCESS, 0, null, ResponseCode.RESPONSE_CODE_SUCCESS.getMessage());
            }else{
                return new BaseResponse(ResponseCode.RESPONSE_CODE_FAILURE,0,null,"没有找到相关计费模板");
            }
        } else {
            return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null,
                    ResponseCode.RESPONSE_CODE_PARAMETER_ERROR.getMessage());
        }
    }

}
