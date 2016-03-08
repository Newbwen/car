package pengyi.application.user.driver;

import pengyi.application.user.driver.command.CreateDriverCommand;
import pengyi.application.user.driver.command.EditDriverCommand;
import pengyi.application.user.driver.representation.DriverRepresentation;

/**
 * Created by YJH on 2016/3/7.
 */
public interface IDriverAppService {

    DriverRepresentation create(CreateDriverCommand command);

    DriverRepresentation edit(EditDriverCommand command);

    DriverRepresentation show(String id);

}
