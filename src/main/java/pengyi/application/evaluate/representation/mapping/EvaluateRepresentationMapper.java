package pengyi.application.evaluate.representation.mapping;

import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pengyi.application.evaluate.representation.EvaluateRepresentation;
import pengyi.application.permission.representation.PermissionRepresentation;
import pengyi.application.user.representation.BaseUserRepresentation;
import pengyi.core.mapping.IMappingService;
import pengyi.domain.model.evaluate.Evaluate;
import pengyi.domain.model.order.Order;
import pengyi.domain.model.permission.Permission;
import pengyi.domain.model.user.BaseUser;
import pengyi.repository.order.OrderResposition;

import java.util.List;

/**
 * Created by YJH on 2016/3/7.
 */
@Component
public class EvaluateRepresentationMapper extends CustomMapper<Evaluate, EvaluateRepresentation> {

    @Autowired
    private IMappingService mappingService;

    public void mapAtoB(Evaluate evaluate,EvaluateRepresentation representation,MappingContext context){
        if(null!=evaluate.getOrder()){
            OrderResposition order = mappingService.map(evaluate.getOrder(),OrderResposition.class,false);
            representation.setOrder(order);
        }
        if(null!=evaluate.getEvaluateUser()){
            BaseUserRepresentation evaluateUser=mappingService.map(evaluate.getEvaluateUser(),BaseUserRepresentation.class,false);
            representation.setEvaluateUser(evaluateUser);
        }

    }
}
