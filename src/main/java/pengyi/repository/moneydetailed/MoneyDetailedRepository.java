package pengyi.repository.moneydetailed;

import org.springframework.stereotype.Repository;
import pengyi.domain.model.moneydetailed.IMoneyDetailedRepository;
import pengyi.domain.model.moneydetailed.MoneyDetailed;
import pengyi.repository.generic.AbstractHibernateGenericRepository;

/**
 * Created by YJH on 2016/3/9.
 */
@Repository("moneyDetailedRepository")
public class MoneyDetailedRepository extends AbstractHibernateGenericRepository<MoneyDetailed, String>
        implements IMoneyDetailedRepository<MoneyDetailed, String> {
}
