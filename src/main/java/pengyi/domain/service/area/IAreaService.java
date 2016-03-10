package pengyi.domain.service.area;

import pengyi.application.area.command.CreateAreaCommand;
import pengyi.application.area.command.EditAreaCommand;
import pengyi.application.area.command.ListAreaCommand;
import pengyi.domain.model.area.Area;
import pengyi.repository.generic.Pagination;

/**
 * Created by YJH on 2016/3/7.
 */
public interface IAreaService {

    Pagination<Area> pagination(ListAreaCommand command);

    Area create(CreateAreaCommand command);

    Area edit(EditAreaCommand command);

    Area show(String id);
}
