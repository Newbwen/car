package pengyi.application.rescue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pengyi.application.rescue.command.CreateRescueCommand;
import pengyi.application.rescue.command.EditRescueCommand;
import pengyi.application.rescue.command.ListRescueCommand;
import pengyi.application.rescue.representation.RescueRepresentation;
import pengyi.application.user.representation.BaseUserRepresentation;
import pengyi.core.api.BaseResponse;
import pengyi.core.api.ResponseCode;
import pengyi.core.api.ResponseMessage;
import pengyi.core.mapping.IMappingService;
import pengyi.core.redis.RedisService;
import pengyi.core.util.CoreStringUtils;
import pengyi.domain.model.rescue.Rescue;
import pengyi.domain.model.user.BaseUser;
import pengyi.domain.service.rescue.IRescueService;
import pengyi.repository.generic.Pagination;

import java.util.List;

/**
 * Created by lvdi on 2016/3/15.
 */
@Service("apiRescueAppService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class ApiRescueAppService implements IApiRescueAppService {

    @Autowired
    private IRescueService rescueService;

    @Autowired
    private IMappingService mappingService;

    @Autowired
    private RedisService redisService;


    @Override
    public BaseResponse apiInfo(String id) {
        RescueRepresentation rescueRepresentation = mappingService.map(rescueService.show(id), RescueRepresentation.class, false);
        return new BaseResponse(ResponseCode.RESPONSE_CODE_SUCCESS, 0, rescueRepresentation, ResponseCode.RESPONSE_CODE_SUCCESS.getMessage());
    }

    @Override
    public Pagination<RescueRepresentation> search(ListRescueCommand command) {
        Pagination<Rescue> pagination = rescueService.pagination(command);
        List<RescueRepresentation> data = mappingService.mapAsList(pagination.getData(), RescueRepresentation.class);
        return new Pagination<RescueRepresentation>(data, pagination.getCount(), pagination.getPage(), pagination.getPageSize());
    }

    @Override
    public BaseResponse updateRescue(EditRescueCommand command) {
        if (null != command) {
            if (CoreStringUtils.isEmpty(command.getId())) {
                return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_10000.getMessage());
            }
            if (null == command.getVersion()) {
                return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_10001.getMessage());
            }
            if (null == command.getDriver()) {
                return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_20000.getMessage());
            }
            if (null == command.getRescueStatus()) {
                return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_20001.getMessage());
            }
            RescueRepresentation rescueRepresentation = mappingService.map(rescueService.apiUpdateRescue(command), RescueRepresentation.class, false);
            return new BaseResponse(ResponseCode.RESPONSE_CODE_SUCCESS, 0, rescueRepresentation, ResponseCode.RESPONSE_CODE_SUCCESS.getMessage());
        } else {
            return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseCode.RESPONSE_CODE_PARAMETER_ERROR.getMessage());
        }

    }

    @Override
    public BaseResponse createRescue(CreateRescueCommand command) {
        if (null != command) {
            if (null == command.getApplyUser()) {
                return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_20005.getMessage());
            }
            if (null == command.getRescueType()) {
                return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_20006.getMessage());
            }
            if (null == command.getDescription()) {
                return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_20003.getMessage());
            }
            if (redisService.exists(command.getUserName())) {
                if (!redisService.getCache(command.getUserName()).equals(command.getVerificationCode())) {
                    return new BaseResponse(ResponseCode.RESPONSE_CODE_VERIFICATION_CODE_ERROR, 0, null,
                            ResponseCode.RESPONSE_CODE_VERIFICATION_CODE_ERROR.getMessage());
                }
            } else {
                return new BaseResponse(ResponseCode.RESPONSE_CODE_VERIFICATION_CODE_NOT_SEND, 0, null,
                        ResponseCode.RESPONSE_CODE_VERIFICATION_CODE_NOT_SEND.getMessage());
            }
            RescueRepresentation rescueRepresentation = mappingService.map(rescueService.create(command), RescueRepresentation.class, false);
            redisService.delete(command.getUserName());
            return new BaseResponse(ResponseCode.RESPONSE_CODE_SUCCESS, 0, rescueRepresentation, ResponseCode.RESPONSE_CODE_SUCCESS.getMessage());
        } else {
            return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseCode.RESPONSE_CODE_PARAMETER_ERROR.getMessage());
        }
    }

    @Override
    public BaseResponse cancelRescue(EditRescueCommand command) {
        if (null != command) {
            if (!CoreStringUtils.isEmpty(command.getId())) {
                return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_10000.getMessage());
            }
            if (null == command.getVersion()) {
                return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_10001.getMessage());
            }
            RescueRepresentation rescueRepresentation = mappingService.map(rescueService.apiCancelRescue(command), RescueRepresentation.class, false);
            return new BaseResponse(ResponseCode.RESPONSE_CODE_SUCCESS, 0, rescueRepresentation, ResponseCode.RESPONSE_CODE_SUCCESS.getMessage());
        } else {

            return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseCode.RESPONSE_CODE_PARAMETER_ERROR.getMessage());
        }

    }

    @Override
    public BaseResponse finishRescue(EditRescueCommand command) {
        if (null != command) {
            if (!CoreStringUtils.isEmpty(command.getId())) {
                return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_10000.getMessage());
            }
            if (null == command.getVersion()) {
                return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_10001.getMessage());
            }
            RescueRepresentation rescueRepresentation = mappingService.map(rescueService.apifinishRescue(command), RescueRepresentation.class, false);
        }
        return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseCode.RESPONSE_CODE_PARAMETER_ERROR.getMessage());
    }


}
