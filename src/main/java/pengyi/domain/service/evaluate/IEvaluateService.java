package pengyi.domain.service.evaluate;

import pengyi.application.evaluate.command.EditEvaluateCommand;
import pengyi.domain.model.evaluate.Evaluate;
import pengyi.domain.model.urlresources.UrlResources;
import pengyi.repository.generic.Pagination;


/**
 * Created by lvdi on 2016/3/8.
 */
public interface IEvaluateService {

    void create(Evaluate evaluate);

    void update(Evaluate evaluate);

    void delete(int evaluateId);

    Pagination<Evaluate> getByUser(String evaluateUserId, int page, int pageSize);

    Pagination<Evaluate> getByOrder(String orderId, int page, int pageSize);

    Evaluate getById(int evaluateId);

    Evaluate edit(EditEvaluateCommand command);

    Evaluate show(String id);

    Evaluate searchByName(String evaluateUserame);



}
