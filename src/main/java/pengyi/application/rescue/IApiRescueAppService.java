package pengyi.application.rescue;

import pengyi.application.rescue.command.CreateRescueCommand;
import pengyi.application.rescue.command.EditRescueCommand;
import pengyi.application.rescue.representation.RescueRepresentation;
import pengyi.core.api.BaseResponse;

import java.util.List;

/**
 * Created by lv on 2016/3/15.
 */
public interface IApiRescueAppService {
    List<RescueRepresentation> allList();

    BaseResponse updateRescue(EditRescueCommand command);

    BaseResponse createRescue(CreateRescueCommand command);

    BaseResponse  cancelRescue(EditRescueCommand command);




}
