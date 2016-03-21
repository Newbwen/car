package pengyi.interfaces.car.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pengyi.application.car.IApiCarAppService;
import pengyi.application.car.command.CreateCarCommand;
import pengyi.application.car.command.EditCarCommand;
import pengyi.application.car.command.ListCarCommand;
import pengyi.application.car.representation.CarRepresentation;
import pengyi.core.api.BaseResponse;
import pengyi.core.api.ResponseCode;
import pengyi.core.api.ResponseMessage;
import pengyi.core.exception.ConcurrencyException;
import pengyi.core.exception.ExistException;

/**
 * Created by LvDi on 2016/3/16.
 */

@Controller
@RequestMapping("/car/api")
public class ApiCarController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private IApiCarAppService apiCarAppService;

    /**
     * 司机查看车辆信息
     */
    @RequestMapping(value = "/check")
    @ResponseBody
    public BaseResponse getBydriver(ListCarCommand command) {
        long startTime = System.currentTimeMillis();
        BaseResponse baseResponse = null;
        try {
            CarRepresentation carRepresentation = apiCarAppService.getBydriver(command.getDriver());
            baseResponse = new BaseResponse(ResponseCode.RESPONSE_CODE_SUCCESS, 0, carRepresentation, ResponseCode.RESPONSE_CODE_SUCCESS.getMessage());
        } catch (Exception e) {
            logger.warn(e.getMessage());
            baseResponse = new BaseResponse(ResponseCode.RESPONSE_CODE_FAILURE, 0, null, e.getMessage());
        }
        baseResponse.setDebug_time(System.currentTimeMillis() - startTime);

        return baseResponse;
    }

    /**
     * (司机)创建车辆信息
     */
    @RequestMapping("/create")
    @ResponseBody
    public BaseResponse create(CreateCarCommand command) {
        long startTime = System.currentTimeMillis();
        BaseResponse baseResponse = null;
        try {
            baseResponse = apiCarAppService.apiCreate(command);
        } catch (ExistException e) {
            logger.warn(e.getMessage());
            baseResponse = new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, ResponseMessage.ERROR_30001, e.getMessage());
        }
        return baseResponse;
    }

    /**
     * (司机)修改车信息
     */
    @RequestMapping(value = "/edit")
    @ResponseBody
    public BaseResponse edit(EditCarCommand command) {
        long startTime = System.currentTimeMillis();
        BaseResponse baseResponse = null;
        try {
            baseResponse=apiCarAppService.updateCar(command);
        }catch (ExistException e){
            logger.warn(e.getMessage());
            baseResponse=new BaseResponse(ResponseCode.RESPONSE_CODE_CONCURRENCY_ERROR,0,ResponseMessage.ERROR_30001,e.getMessage());
        }
        return baseResponse;
    }
    /**
     *(公司)查看车辆信息
     */



}
