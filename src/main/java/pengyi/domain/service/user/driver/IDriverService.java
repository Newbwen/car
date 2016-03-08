package pengyi.domain.service.user.driver;

import pengyi.application.user.driver.command.CreateDriverCommand;
import pengyi.application.user.driver.command.EditDriverCommand;
import pengyi.domain.model.user.driver.Driver;

/**
 * Created by YJH on 2016/3/7.
 */
public interface IDriverService {

    Driver create(CreateDriverCommand command);

    Driver edit(EditDriverCommand command);

    Driver show(String id);

}
