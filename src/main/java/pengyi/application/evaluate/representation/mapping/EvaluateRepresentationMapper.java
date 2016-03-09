package pengyi.application.evaluate.representation.mapping;

import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pengyi.application.evaluate.representation.EvaluateRepresentation;
import pengyi.application.order.representation.OrderRepresentation;
import pengyi.application.permission.representation.PermissionRepresentation;
import pengyi.application.user.representation.BaseUserRepresentation;
import pengyi.core.mapping.IMappingService;
import pengyi.domain.model.evaluate.Evaluate;


/**
 * Created by YJH on 2016/3/7.
 */
@Component
public class EvaluateRepresentationMapper extends CustomMapper<Evaluate, EvaluateRepresentation> {

    @Autowired
    private IMappingService mappingService;

    public void mapAtoB(Evaluate evaluate,EvaluateRepresentation representation,MappingContext context){
        if(null!=evaluate.getOrder()){
            OrderRepresentation order = mappingService.map(evaluate.getOrder(),OrderRepresentation.class,false);
            representation.setOrder(order);
        }
        if(null!=evaluate.getEvaluateUser()){
            BaseUserRepresentation evaluateUser=mappingService.map(evaluate.getEvaluateUser(),BaseUserRepresentation.class,false);
            representation.setEvaluateUser(evaluateUser);
        }

    }
}
