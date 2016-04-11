package pengyi.application.recharge.representation.mapping;

import ma.glasnost.orika.CustomMapper;
import org.springframework.stereotype.Component;
import pengyi.application.recharge.representation.RechargeRepresentation;
import pengyi.domain.model.recharge.Recharge;

/**
 * Created by pengyi on 2016/4/11.
 */
@Component
public class RechargeRepresentationMapper extends CustomMapper<Recharge, RechargeRepresentation> {
}
