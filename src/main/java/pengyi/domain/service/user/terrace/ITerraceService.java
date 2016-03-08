package pengyi.domain.service.user.terrace;

import pengyi.application.user.terrace.command.CreateTerraceCommand;
import pengyi.application.user.terrace.command.EditTerraceCommand;
import pengyi.domain.model.user.terrace.Terrace;

/**
 * Created by YJH on 2016/3/7.
 */
public interface ITerraceService {

    Terrace create(CreateTerraceCommand command);

    Terrace edit(EditTerraceCommand command);

    Terrace show(String id);

}
