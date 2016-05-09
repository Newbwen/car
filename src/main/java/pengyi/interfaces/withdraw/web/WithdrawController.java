package pengyi.interfaces.withdraw.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pengyi.application.user.representation.BaseUserRepresentation;
import pengyi.application.withdraw.IWithdrawAppService;
import pengyi.application.withdraw.command.CreateWithdrawCommand;
import pengyi.application.withdraw.command.EditWithdrawCommand;
import pengyi.application.withdraw.command.ListWithdrawCommand;
import pengyi.core.api.BaseResponse;
import pengyi.core.api.ResponseCode;
import pengyi.core.commons.Constants;
import pengyi.domain.model.user.BaseUser;
import pengyi.interfaces.shared.web.AlertMessage;
import pengyi.interfaces.shared.web.BaseController;

import javax.servlet.http.HttpSession;

/**
 * Created by pengyi on 2016/5/6.
 */
@Controller
@RequestMapping("/withdraw")
public class WithdrawController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IWithdrawAppService withdrawAppService;

    @RequestMapping(value = "/finish")
    public ModelAndView finish(EditWithdrawCommand command, RedirectAttributes redirectAttributes, HttpSession session) {
        BaseUser user = (BaseUser) session.getAttribute(Constants.SESSION_USER);
        command.setLoginUser(user.getId());
        AlertMessage alertMessage;
        try {
            withdrawAppService.finish(command);
        } catch (Exception e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, e.getMessage());
            redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
            return new ModelAndView("redirect:/withdraw/list").addObject(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        }
        alertMessage = new AlertMessage(AlertMessage.MessageType.SUCCESS, AlertMessage.MessageType.SUCCESS.getName());
        redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        return new ModelAndView("redirect:/withdraw/list").addObject(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
    }

    @RequestMapping(value = "/list")
    public ModelAndView list(ListWithdrawCommand command) {
        return new ModelAndView("/withdraw/list", "pagination", withdrawAppService.pagination(command))
                .addObject("command", command);
    }

}
