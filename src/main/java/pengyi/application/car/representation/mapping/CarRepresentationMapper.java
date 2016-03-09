package pengyi.application.car.representation.mapping;

import ma.glasnost.orika.CustomMapper;
import org.springframework.stereotype.Component;
import pengyi.application.car.representation.CarRepresentation;
import pengyi.domain.model.car.Car;

/**
 * Created by lvdi on 2016/3/8.
 */
@Component
public class CarRepresentationMapper extends CustomMapper<Car, CarRepresentation> {
}
