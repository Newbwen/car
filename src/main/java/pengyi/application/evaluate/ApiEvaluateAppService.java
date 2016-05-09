package pengyi.application.evaluate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pengyi.application.car.ICarAppService;
import pengyi.application.car.representation.CarRepresentation;
import pengyi.application.evaluate.command.CreateEvaluateCommand;
import pengyi.application.evaluate.command.EditEvaluateCommand;
import pengyi.application.evaluate.representation.EvaluateRepresentation;
import pengyi.core.api.BaseResponse;
import pengyi.core.api.ResponseCode;
import pengyi.core.api.ResponseMessage;
import pengyi.core.mapping.IMappingService;
import pengyi.core.util.CoreStringUtils;
import pengyi.domain.service.evaluate.IEvaluateService;

/**
 * Created by LvDi on 2016/3/17.
 */

@Service("apiEvaluateAppService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class ApiEvaluateAppService implements IApiEvaluateAppService {

    @Autowired
    private IEvaluateService evaluateService;
    @Autowired
    private IMappingService mappingService;

    @Autowired
    private ICarAppService carAppService;

    @Override
    public BaseResponse createEvaluate(CreateEvaluateCommand command) {
        if (null != command) {
            if (CoreStringUtils.isEmpty(command.getOrderId())) {
                return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_10024.getMessage());
            }
            if (null == command.getLevel()) {
                return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_10024.getMessage());
            }
            evaluateService.apiCreateEvaluate(command);
            return new BaseResponse(ResponseCode.RESPONSE_CODE_SUCCESS, 0, null, ResponseCode.RESPONSE_CODE_SUCCESS.getMessage());
        } else {
            return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseCode.RESPONSE_CODE_PARAMETER_ERROR.getMessage());
        }
    }

    @Override
    public EvaluateRepresentation show(String orderId) {
        return mappingService.map(evaluateService.show(orderId), EvaluateRepresentation.class, false);
    }

    @Override
    public BaseResponse edit(EditEvaluateCommand command) {
        if (null != command) {
            if (CoreStringUtils.isEmpty(command.getId())) {
                return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_10000.getMessage());
            }
            if (null == command.getVersion()) {
                return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_10001.getMessage());
            }
            if (null == command.getContent()) {
                return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_40001.getMessage());
            }
            evaluateService.edit(command);
            return new BaseResponse(ResponseCode.RESPONSE_CODE_SUCCESS, 0, null, ResponseCode.RESPONSE_CODE_SUCCESS.getMessage());
        } else {
            return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseCode.RESPONSE_CODE_PARAMETER_ERROR.getMessage());
        }
    }

    @Override
    public BaseResponse delete(String orderId) {
        if (null != orderId) {
            evaluateService.delete(orderId);
            return new BaseResponse(ResponseCode.RESPONSE_CODE_SUCCESS, 0, null, ResponseCode.RESPONSE_CODE_SUCCESS.getMessage());
        } else {
            return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseCode.RESPONSE_CODE_PARAMETER_ERROR.getMessage());
        }

    }

    @Override
    public BaseResponse getByOrderId(String orderId, String userId) {
        if (!CoreStringUtils.isEmpty(orderId)) {
            EvaluateRepresentation data = mappingService.map(evaluateService.searchByOrder(orderId, userId), EvaluateRepresentation.class, false);
            CarRepresentation car = carAppService.searchByDriver(data.getOrder().getReceiveUser().getId());
            if (null != car) {
                data.setCar(car);
            }
            return new BaseResponse(ResponseCode.RESPONSE_CODE_SUCCESS, 0, data, ResponseCode.RESPONSE_CODE_SUCCESS.getMessage());
        } else {
            return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_10024.getMessage());
        }
    }


}
