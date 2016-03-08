package pengyi.application.user.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pengyi.application.user.driver.command.CreateDriverCommand;
import pengyi.application.user.driver.command.EditDriverCommand;
import pengyi.application.user.driver.representation.DriverRepresentation;
import pengyi.core.mapping.IMappingService;
import pengyi.domain.service.user.driver.IDriverService;

/**
 * Created by YJH on 2016/3/7.
 */
@Service("driverAppService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class DriverAppService implements IDriverAppService {

    @Autowired
    private IDriverService driverService;

    @Autowired
    private IMappingService mappingService;

    @Override
    public DriverRepresentation create(CreateDriverCommand command) {
        return mappingService.map(driverService.create(command), DriverRepresentation.class, false);
    }

    @Override
    public DriverRepresentation edit(EditDriverCommand command) {
        return mappingService.map(driverService.edit(command), DriverRepresentation.class, false);
    }

    @Override
    public DriverRepresentation show(String id) {
        return mappingService.map(driverService.show(id), DriverRepresentation.class, false);
    }
}
