package pengyi.application.user.terrace;

import pengyi.application.user.terrace.command.CreateTerraceCommand;
import pengyi.application.user.terrace.command.EditTerraceCommand;
import pengyi.application.user.terrace.representation.TerraceRepresentation;

/**
 * Created by YJH on 2016/3/7.
 */
public interface ITerraceAppService {

    TerraceRepresentation create(CreateTerraceCommand command);

    TerraceRepresentation edit(EditTerraceCommand command);

    TerraceRepresentation show(String id);

}
