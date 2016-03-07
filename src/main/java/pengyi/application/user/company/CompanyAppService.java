package pengyi.application.user.company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pengyi.domain.service.user.company.ICompanyService;

/**
 * Created by YJH on 2016/3/7.
 */
@Service("companyAppService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class CompanyAppService implements ICompanyAppService {

    @Autowired
    private ICompanyService companyService;

}
