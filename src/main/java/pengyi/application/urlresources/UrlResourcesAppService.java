package pengyi.application.urlresources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pengyi.application.urlresources.command.CreateUrlResourcesCommand;
import pengyi.application.urlresources.command.EditUrlResourcesCommand;
import pengyi.application.urlresources.command.ListUrlResourcesCommand;
import pengyi.application.urlresources.representation.UrlResourcesRepresentation;
import pengyi.core.commons.command.EditStatusCommand;
import pengyi.core.mapping.IMappingService;
import pengyi.core.shiro.ShiroFilterChainManager;
import pengyi.domain.model.urlresources.UrlResources;
import pengyi.domain.service.urlresources.IUrlResourcesService;
import pengyi.repository.generic.Pagination;

import java.util.List;

/**
 * Created by YJH on 2016/3/7.
 */
@Service("urlResourcesAppService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class UrlResourcesAppService implements IUrlResourcesAppService {

    @Autowired
    private IUrlResourcesService urlResourcesService;

    @Autowired
    private IMappingService mappingService;

    @Override
    @Transactional(readOnly = true)
    public List<UrlResources> findAllUrlResources() {
        return urlResourcesService.findAllUrlResources();
    }

    @Override
    @Transactional(readOnly = true)
    public Pagination<UrlResourcesRepresentation> pagination(ListUrlResourcesCommand command) {
        command.verifyPage();
        command.verifyPageSize(20);

        Pagination<UrlResources> pagination = urlResourcesService.pagination(command);
        List<UrlResourcesRepresentation> data = mappingService.mapAsList(pagination.getData(), UrlResourcesRepresentation.class);
        return new Pagination<UrlResourcesRepresentation>(data, pagination.getCount(), pagination.getPage(), pagination.getPageSize());
    }

    @Override
    public UrlResourcesRepresentation create(CreateUrlResourcesCommand command) {
        return mappingService.map(urlResourcesService.create(command), UrlResourcesRepresentation.class, false);
    }

    @Override
    public UrlResourcesRepresentation edit(EditUrlResourcesCommand command) {
        return mappingService.map(urlResourcesService.edit(command), UrlResourcesRepresentation.class, false);
    }

    @Override
    public UrlResourcesRepresentation show(String id) {
        return mappingService.map(urlResourcesService.show(id), UrlResourcesRepresentation.class, false);
    }

    @Override
    public UrlResourcesRepresentation updateStatus(EditStatusCommand command) {
        return mappingService.map(urlResourcesService.updateStatus(command), UrlResourcesRepresentation.class, false);
    }
}
