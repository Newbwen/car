package pengyi.repository.rescue;

import org.springframework.stereotype.Repository;
import pengyi.domain.model.rescue.IRescueRepository;
import pengyi.domain.model.rescue.Rescue;
import pengyi.repository.generic.AbstractHibernateGenericRepository;

/**
 * Author: lvdi
 * Date: 16-3-8
 */
@Repository("rescueRepository")
public class RescueResposition extends AbstractHibernateGenericRepository<Rescue, String> implements IRescueRepository<Rescue, String> {
}