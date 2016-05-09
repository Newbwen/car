package pengyi.application.withhold;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pengyi.application.withhold.command.CreateWithholdCommand;
import pengyi.application.withhold.command.ListWithholdCommand;
import pengyi.application.withhold.representation.WithholdRepresentation;
import pengyi.core.mapping.IMappingService;
import pengyi.domain.model.withhold.Withhold;
import pengyi.domain.service.withhold.IWithholdService;
import pengyi.repository.generic.Pagination;

import java.util.List;

/**
 * Created by pengyi on 2016/5/6.
 */
@Service("withholdAppService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class WithholdAppService implements IWithholdAppService {

    @Autowired
    private IWithholdService withholdService;
    @Autowired
    private IMappingService mappingService;

    @Override
    public void create(CreateWithholdCommand command) {
        withholdService.create(command);
    }

    @Override
    @Transactional(readOnly = true)
    public Pagination<WithholdRepresentation> pagination(ListWithholdCommand command) {
        command.verifyPage();
        command.verifyPageSize(20);
        Pagination<Withhold> withholdPagination = withholdService.pagination(command);
        List<WithholdRepresentation> representations = mappingService.mapAsList(withholdPagination.getData(), WithholdRepresentation.class);
        return new Pagination<WithholdRepresentation>(representations, withholdPagination.getCount(), command.getPage(), command.getPageSize());
    }

    @Override
    @Transactional(readOnly = true)
    public WithholdRepresentation show(String id) {
        return mappingService.map(withholdService.show(id), WithholdRepresentation.class, false);
    }
}
