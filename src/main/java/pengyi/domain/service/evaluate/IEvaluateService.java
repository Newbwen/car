package pengyi.domain.service.evaluate;

import pengyi.application.car.command.ListCarCommand;
import pengyi.application.evaluate.command.CreateEvaluateCommand;
import pengyi.application.evaluate.command.EditEvaluateCommand;
import pengyi.application.evaluate.command.ListEvaluateCommand;
import pengyi.domain.model.evaluate.Evaluate;
import pengyi.domain.model.urlresources.UrlResources;
import pengyi.repository.generic.Pagination;


/**
 * Created by lvdi on 2016/3/8.
 */
public interface IEvaluateService {


    Pagination<Evaluate> pagination(ListEvaluateCommand command);


    Evaluate edit(EditEvaluateCommand command);

    Evaluate show(String id);

    Evaluate searchByName(String evaluateUserame);


    Evaluate create(CreateEvaluateCommand command);
}
