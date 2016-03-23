package pengyi.application.billing;

import pengyi.core.api.BaseResponse;

/**
 * Created by YJH on 2016/3/22.
 */
public interface IApiBillingAppService {
    BaseResponse searchByArea(String driverId);
}
