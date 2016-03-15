package pengyi.application.rescue;

import pengyi.application.rescue.representation.RescueRepresentation;

import java.util.List;

/**
 * Created by lv on 2016/3/15.
 */
public interface IApiRescueAppService {
    List<RescueRepresentation> allList();
}
