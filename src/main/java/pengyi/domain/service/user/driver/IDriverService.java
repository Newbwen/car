package pengyi.domain.service.user.driver;

import pengyi.application.user.driver.command.EditDriverCommand;
import pengyi.application.user.driver.command.BaseListDriverCommand;
import pengyi.domain.model.user.BaseUser;
import pengyi.domain.model.user.driver.Driver;
import pengyi.repository.generic.Pagination;

/**
 * Created by YJH on 2016/3/7.
 */
public interface IDriverService {

    Pagination<Driver> pagination(BaseListDriverCommand command);

    Driver edit(EditDriverCommand command);

    Driver show(String id);

    Driver create(Driver driver);
}
