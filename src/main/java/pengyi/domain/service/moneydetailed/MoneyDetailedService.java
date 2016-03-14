package pengyi.domain.service.moneydetailed;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pengyi.application.moneydetailed.command.CreateMoneyDetailedCommand;
import pengyi.application.moneydetailed.command.EditMoneyDetailedCommand;
import pengyi.application.moneydetailed.command.ListMoneyDetailedCommand;
import pengyi.core.exception.NoFoundException;
import pengyi.core.util.CoreStringUtils;
import pengyi.domain.model.moneydetailed.IMoneyDetailedRepository;
import pengyi.domain.model.moneydetailed.MoneyDetailed;
import pengyi.domain.model.user.BaseUser;
import pengyi.domain.service.user.IBaseUserService;
import pengyi.repository.generic.Pagination;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by YJH on 2016/3/9.
 */
@Service("moneyDetailedService")
public class MoneyDetailedService implements IMoneyDetailedService {

    @Autowired
    private IMoneyDetailedRepository<MoneyDetailed, String> moneyDetailedRepository;

    @Autowired
    private IBaseUserService baseUserService;

    @Override
    public Pagination<MoneyDetailed> pagination(ListMoneyDetailedCommand command) {
        List<Criterion> criterionList = new ArrayList<Criterion>();

        if (!CoreStringUtils.isEmpty(command.getUserName())) {
            criterionList.add(Restrictions.like("baseUser.userName", command.getUserName(), MatchMode.ANYWHERE));
        }

        List<Order> orders = new ArrayList<Order>();
        orders.add(Order.desc("createDate"));

        return moneyDetailedRepository.pagination(command.getPage(), command.getPageSize(), criterionList, orders);
    }

    @Override
    public MoneyDetailed create(CreateMoneyDetailedCommand command) {
        BaseUser user = baseUserService.show(command.getBaseUser());

        MoneyDetailed moneyDetailed = new MoneyDetailed(user, command.getFlowType(), command.getMoney(),
                command.getExplain(), command.getOldMoney(), command.getNewMoney(), new Date());

        moneyDetailedRepository.save(moneyDetailed);

        return moneyDetailed;
    }

    @Override
    public MoneyDetailed edit(EditMoneyDetailedCommand command) {
        MoneyDetailed moneyDetailed = this.show(command.getId());
        moneyDetailed.fainWhenConcurrencyViolation(command.getVersion());

        //TODO 修改内容 在定

        return moneyDetailed;
    }

    @Override
    public MoneyDetailed show(String id) {
        MoneyDetailed moneyDetailed = moneyDetailedRepository.getById(id);
        if(null == moneyDetailed){
            throw new NoFoundException("没有找到资金流向id=[" + id + "]的记录");
        }
        return moneyDetailed;
    }
}
