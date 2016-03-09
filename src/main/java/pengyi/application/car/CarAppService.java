package pengyi.application.car;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pengyi.application.car.command.CreateCarCommand;
import pengyi.application.car.command.EditCarCommand;
import pengyi.application.car.command.ListCarCommand;
import pengyi.application.car.representation.CarRepresentation;
import pengyi.core.mapping.IMappingService;
import pengyi.repository.generic.Pagination;

/**
 * Created by YJH on 2016/3/7.
 */
@Service("permissionAppService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class CarAppService implements ICarAppService {

    @Autowired
    private ICarAppService carService;

    @Autowired
    private IMappingService mappingService;


    @Override
    @Transactional(readOnly = true)
    public Pagination<CarRepresentation> pagination(ListCarCommand command) {
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public CarRepresentation create(CreateCarCommand command) {
        return mappingService.map(carService.create(command), CarRepresentation.class, false);
    }

    @Override
    @Transactional(readOnly = true)
    public CarRepresentation edit(EditCarCommand command) {
        return mappingService.map(carService.edit(command), CarRepresentation.class, false);
    }

    @Override
    @Transactional(readOnly = true)
    public CarRepresentation show(String id) {
        return mappingService.map(carService.show(id), CarRepresentation.class, false);
    }
}
