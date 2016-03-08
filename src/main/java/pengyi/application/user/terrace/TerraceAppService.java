package pengyi.application.user.terrace;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pengyi.application.user.terrace.command.CreateTerraceCommand;
import pengyi.application.user.terrace.command.EditTerraceCommand;
import pengyi.application.user.terrace.representation.TerraceRepresentation;
import pengyi.core.mapping.IMappingService;
import pengyi.domain.service.user.terrace.ITerraceService;

/**
 * Created by YJH on 2016/3/7.
 */
@Service("terraceAppService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class TerraceAppService implements ITerraceAppService {

    @Autowired
    private ITerraceService terraceService;

    @Autowired
    private IMappingService mappingService;

    @Override
    public TerraceRepresentation create(CreateTerraceCommand command) {
        return mappingService.map(terraceService.create(command), TerraceRepresentation.class, false);
    }

    @Override
    public TerraceRepresentation edit(EditTerraceCommand command) {
        return mappingService.map(terraceService.edit(command), TerraceRepresentation.class, false);
    }

    @Override
    public TerraceRepresentation show(String id) {
        return mappingService.map(terraceService.show(id), TerraceRepresentation.class, false);
    }
}
