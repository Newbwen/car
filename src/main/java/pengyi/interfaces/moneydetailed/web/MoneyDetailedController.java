package pengyi.interfaces.moneydetailed.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pengyi.application.moneydetailed.IMoneyDetailedAppService;
import pengyi.application.moneydetailed.command.ListMoneyDetailedCommand;
import pengyi.application.moneydetailed.representation.MoneyDetailedRepresentation;
import pengyi.interfaces.shared.web.AlertMessage;
import pengyi.interfaces.shared.web.BaseController;

import java.util.Locale;

/**
 * Created by YJH on 2016/3/14.
 */
@Controller
@RequestMapping("/money_detailed")
public class MoneyDetailedController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IMoneyDetailedAppService moneyDetailedAppService;

    @RequestMapping(value = "/list")
    public ModelAndView list(ListMoneyDetailedCommand command) {
        return new ModelAndView("/moneydetailed/list", "pagination", moneyDetailedAppService.pagination(command))
                .addObject("command", command);
    }

    @RequestMapping(value = "/show/{id}")
    public ModelAndView show(@PathVariable String id, RedirectAttributes redirectAttributes, Locale locale) {
        AlertMessage alertMessage;
        MoneyDetailedRepresentation moneyDetailed = null;
        try {
            moneyDetailed = moneyDetailedAppService.show(id);
        } catch (Exception e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, e.getMessage());
            redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
            return new ModelAndView("redirect:/money_detailed/list");
        }

        return new ModelAndView("/moneydetailed/show", "moneyDetailed", moneyDetailed);
    }

}
