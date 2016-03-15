package pengyi.application.rescue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pengyi.application.rescue.representation.RescueRepresentation;
import pengyi.core.mapping.IMappingService;
import pengyi.domain.service.rescue.IRescueService;

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


    @Override
    public List<RescueRepresentation> allList() {
        return mappingService.mapAsList(rescueService.findAllRescue(),RescueRepresentation.class);
    }
}
