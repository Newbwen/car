package pengyi.application.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pengyi.application.message.representation.MessageRepresentation;
import pengyi.core.mapping.IMappingService;
import pengyi.domain.service.message.IMessageService;

/**
 * Created by liubowen on 2016/3/15.
 */
@Service("apiMessageService")
@Transactional(propagation = Propagation.REQUIRED,readOnly = false,rollbackFor = Exception.class)
public class ApiMessageService implements IApiMessageService{

    @Autowired
    private IMessageService messageService;

    @Autowired
    private IMappingService mappingService;

    @Override
    @Transactional(readOnly = true)
    public MessageRepresentation apiSearchByCompany(String companyId) {

        return mappingService.map(messageService.show(companyId), MessageRepresentation.class,false);
    }
}
