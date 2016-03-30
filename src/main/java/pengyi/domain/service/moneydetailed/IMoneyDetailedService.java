package pengyi.domain.service.moneydetailed;

import pengyi.application.moneydetailed.command.CreateMoneyDetailedCommand;
import pengyi.application.moneydetailed.command.EditMoneyDetailedCommand;
import pengyi.application.moneydetailed.command.ListMoneyDetailedCommand;
import pengyi.domain.model.moneydetailed.MoneyDetailed;
import pengyi.repository.generic.Pagination;

/**
 * Created by YJH on 2016/3/9.
 */
public interface IMoneyDetailedService {

    Pagination<MoneyDetailed> pagination(ListMoneyDetailedCommand command);

    MoneyDetailed create(CreateMoneyDetailedCommand command);

    MoneyDetailed edit(EditMoneyDetailedCommand command);

    MoneyDetailed show(String id);
}
