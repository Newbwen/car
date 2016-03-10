package pengyi.domain.service.rescue;

import pengyi.application.rescue.command.CreateRescueCommand;
import pengyi.application.rescue.command.EditRescueCommand;
import pengyi.application.rescue.command.ListRescueCommand;
import pengyi.domain.model.rescue.Rescue;
import pengyi.repository.generic.Pagination;


/**
 * Created by lvdi on 2015/3/8.
 */
public interface IRescueService {


    Rescue getById(int rescueId);

    Pagination pagination(ListRescueCommand command);

    Rescue create(CreateRescueCommand command);

    Rescue edit(EditRescueCommand command);

    Rescue show(String id);

    Rescue updateStatus(EditRescueCommand command);

    Rescue searchByName(String rescueName);

}
