package pengyi.domain.service.rescue;

import pengyi.application.rescue.command.CreateRescueCommand;
import pengyi.application.rescue.command.EditRescueCommand;
import pengyi.application.rescue.command.ListRescueCommand;
import pengyi.domain.model.rescue.Rescue;
import pengyi.repository.generic.Pagination;

import java.util.List;


/**
 * Created by lvdi on 2015/3/8.
 */
public interface IRescueService {

//    List<Rescue> findAllRescue();

    Rescue getById(int rescueId);

    Pagination pagination(ListRescueCommand command);

    Rescue create(CreateRescueCommand command);

    Rescue edit(EditRescueCommand command);

    Rescue show(String id);

    Rescue updateStatus(EditRescueCommand command);

    Rescue searchByName(String rescueName);

    /********** api 方法    *************/
    Rescue apiUpdateRescue(EditRescueCommand command);

    Rescue apiCancelRescue(EditRescueCommand command);

    Rescue apifinishRescue(EditRescueCommand command);

    Pagination searchRescue(ListRescueCommand command);





}
