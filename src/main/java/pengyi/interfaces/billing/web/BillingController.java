package pengyi.interfaces.billing.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pengyi.application.billing.IBillingAppService;
import pengyi.application.billing.command.CreateBillingCommand;
import pengyi.application.billing.command.EditBillingCommand;
import pengyi.application.billing.command.ListBillingCommand;
import pengyi.application.billing.representation.BillingRepresentation;
import pengyi.core.exception.ConcurrencyException;
import pengyi.interfaces.shared.web.AlertMessage;
import pengyi.interfaces.shared.web.BaseController;

import javax.validation.Valid;
import java.util.Date;
import java.util.Locale;

/**
 * Created by YJH on 2016/3/21.
 */
@Controller
@RequestMapping("/billing")
public class BillingController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IBillingAppService billingAppService;

    @RequestMapping(value = "/list")
    public ModelAndView list(ListBillingCommand command){
        return new ModelAndView("/billing/list","command",command).addObject("pagination",billingAppService.pagination(command));
    }
    @RequestMapping(value = "/create",method = RequestMethod.GET)
    public ModelAndView create(@ModelAttribute("command") CreateBillingCommand command){
        return new ModelAndView("/billing/create","command",command);
    }

    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public ModelAndView create(@Valid @ModelAttribute("command") CreateBillingCommand command,
                               BindingResult bindingResult, RedirectAttributes redirectAttributes, Locale locale){
        if(bindingResult.hasErrors()){
            return new ModelAndView("/billing/create");
        }
        AlertMessage alertMessage=null;

        BillingRepresentation billing=null;
        try {
            billing=billingAppService.create(command);
        }catch (Exception e){
            logger.warn(e.getMessage());
            alertMessage=new AlertMessage(AlertMessage.MessageType.WARNING,e.getMessage());
            return new ModelAndView("/billing/create","command",command)
                    .addObject(AlertMessage.MODEL_ATTRIBUTE_KEY,alertMessage);
        }
        logger.info("创建计费成功kmBilling=[" + billing.getKmBilling() + "],时间[" + new Date() + "]");

        alertMessage = new AlertMessage(this.getMessage("default.create.success.message", null, locale));

        redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        redirectAttributes.addAttribute("id", billing.getId());

        return new ModelAndView("redirect:/billing/show/{id}");
    }

    @RequestMapping(value = "/show/{id}",method = RequestMethod.GET)
    public ModelAndView show(@PathVariable String id,RedirectAttributes redirectAttributes,Locale locale){
        AlertMessage alertMessage;
        BillingRepresentation billing=null;
        try {
            billing=billingAppService.show(id);
        }catch (Exception e){
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, e.getMessage());
            redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
            return new ModelAndView("redirect:/billing/list");
        }
        return new ModelAndView("/billing/show","billing",billing);

    }
    @RequestMapping(value = "/edit/{id}")
    public ModelAndView edit(@PathVariable String id, @ModelAttribute("command")EditBillingCommand command,
                             RedirectAttributes redirectAttributes,Locale locale){
        AlertMessage alertMessage;
        BillingRepresentation billing=null;
        try {
            billing=billingAppService.show(id);
        }catch (Exception e){
            logger.warn(e.getMessage());
            alertMessage=new AlertMessage(AlertMessage.MessageType.WARNING,e.getMessage());
            redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY,alertMessage);
            return new ModelAndView("redirect:/billing/list");
        }
        return new ModelAndView("/billing/edit","command",command).addObject("billing",billing);

    }

    @RequestMapping(value = "/edit",method = RequestMethod.POST)
    public ModelAndView edit(@Valid @ModelAttribute("command")EditBillingCommand command,BindingResult bindingResult,
                             RedirectAttributes redirectAttributes,Locale locale){
        if(bindingResult.hasErrors()){
            return new ModelAndView("/billing/edit", "command", command);
        }
        AlertMessage alertMessage;
        BillingRepresentation billing=null;
        try {
            billing=billingAppService.edit(command);
        }catch (ConcurrencyException e){
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING,e.getMessage());
            redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
            redirectAttributes.addAttribute("id", command.getId());
            return new ModelAndView("redirect:/billing/edit/{id}");
        }catch (Exception e){
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, e.getMessage());
            return new ModelAndView("/billing/edit", "command", command)
                    .addObject(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        }
        logger.info("修改billing成功id=[" + billing.getId() + "],时间[" + new Date() + "]");
        alertMessage = new AlertMessage(this.getMessage("default.edit.success.message", null, locale));
        redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        redirectAttributes.addAttribute("id", billing.getId());

        return new ModelAndView("redirect:/billing/show/{id}");

    }

}
