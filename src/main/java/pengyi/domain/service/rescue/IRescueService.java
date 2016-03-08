package pengyi.domain.service.rescue;

import org.gjt.mm.mysql.Driver;
import pengyi.domain.model.rescue.Rescue;
import pengyi.domain.model.user.BaseUser;
import pengyi.repository.generic.Pagination;


/**
 * Created by lvdi on 2015/3/8.
 */
public interface IRescueService {

    void save(Rescue rescue);

    void delete(int rescueId);

    void upadte(Rescue rescue);

    Pagination<Rescue> getRescueList(BaseUser applyUser, int page, int pageSize);

    Pagination<Rescue> getRescueList(Driver driver, int page, int pageSize);

    Rescue getById(int rescueId);

}
