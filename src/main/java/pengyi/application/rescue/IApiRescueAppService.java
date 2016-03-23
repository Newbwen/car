package pengyi.application.rescue;

import pengyi.application.rescue.command.CreateRescueCommand;
import pengyi.application.rescue.command.EditRescueCommand;
import pengyi.application.rescue.command.ListRescueCommand;
import pengyi.application.rescue.representation.RescueRepresentation;
import pengyi.core.api.BaseResponse;
import pengyi.repository.generic.Pagination;

import java.util.List;

/**
 * Created by lv on 2016/3/15.
 */
public interface IApiRescueAppService {
    Pagination<RescueRepresentation> search(ListRescueCommand command);

    BaseResponse updateRescue(EditRescueCommand command);

    BaseResponse createRescue(CreateRescueCommand command);

    BaseResponse cancelRescue(EditRescueCommand command);

    BaseResponse finishRescue(EditRescueCommand command);




}
