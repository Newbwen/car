package pengyi.application.user.terrace;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pengyi.domain.service.user.terrace.ITerraceService;

/**
 * Created by YJH on 2016/3/7.
 */
@Service("terraceAppService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class TerraceAppService implements ITerraceAppService {

    @Autowired
    private ITerraceService terraceService;

}
