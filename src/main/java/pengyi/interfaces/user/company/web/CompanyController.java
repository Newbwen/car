package pengyi.interfaces.user.company.web;

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
import pengyi.application.user.company.ICompanyAppService;
import pengyi.application.user.company.command.CreateCompanyCommand;
import pengyi.application.user.company.command.EditCompanyCommand;
import pengyi.application.user.company.command.BaseListCompanyCommand;
import pengyi.application.user.company.representation.CompanyRepresentation;
import pengyi.core.exception.ConcurrencyException;
import pengyi.interfaces.shared.web.AlertMessage;
import pengyi.interfaces.shared.web.BaseController;

import javax.validation.Valid;
import java.util.Date;
import java.util.Locale;

/**
 * Created by YJH on 2016/3/11.
 */
@Controller
@RequestMapping("/user/company")
public class CompanyController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ICompanyAppService companyAppService;

    @RequestMapping(value = "/list")
    public ModelAndView list(BaseListCompanyCommand command) {
        return new ModelAndView("/user/company/list", "command", command)
                .addObject("pagination", companyAppService.pagination(command));
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView create(@ModelAttribute("command") CreateCompanyCommand command) {
        return new ModelAndView("/user/company/create", "command", command);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ModelAndView create(@Valid @ModelAttribute("command") CreateCompanyCommand command,
                               BindingResult bindingResult, RedirectAttributes redirectAttributes,
                               Locale locale) {

        if (bindingResult.hasErrors()) {
            return new ModelAndView("/user/company/create", "command", command);
        }

        AlertMessage alertMessage = null;

        CompanyRepresentation company = null;

        try {
            company = companyAppService.create(command);
        } catch (Exception e) {
            logger.error(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, e.getMessage());
            return new ModelAndView("/user/company/create", "command", command)
                    .addObject(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        }

        logger.info("创建company(公司)用户成功id=[" + company.getId() + "],时间[" + new Date() + "]");

        alertMessage = new AlertMessage(this.getMessage("default.create.success.message", null, locale));
        redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        redirectAttributes.addAttribute("id", company.getId());
        return new ModelAndView("redirect:/user/company/show/{id}");
    }

    @RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
    public ModelAndView show(@PathVariable String id, RedirectAttributes redirectAttributes, Locale locale) {
        AlertMessage alertMessage;
        CompanyRepresentation company = null;
        try {
            company = companyAppService.show(id);
        } catch (Exception e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, e.getMessage());
            redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
            return new ModelAndView("redirect:/user/company/list");
        }
        return new ModelAndView("/user/company/show", "company", company);
    }

    @RequestMapping(value = "/edit/{id}")
    public ModelAndView edit(@PathVariable String id, @ModelAttribute("command") EditCompanyCommand command,
                             RedirectAttributes redirectAttributes, Locale locale) {

        AlertMessage alertMessage;

        CompanyRepresentation company = null;

        try {
            company = companyAppService.show(id);
        } catch (Exception e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, e.getMessage());
            redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
            return new ModelAndView("redirect:/user/company/list");
        }

        return new ModelAndView("/user/company/edit", "command", command).addObject("company", company);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ModelAndView edit(@Valid @ModelAttribute("command") EditCompanyCommand command, BindingResult bindingResult,
                             RedirectAttributes redirectAttributes, Locale locale) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("/user/company/edit", "command", command);
        }

        AlertMessage alertMessage;
        CompanyRepresentation company = null;

        try {
            company = companyAppService.edit(command);
        } catch (ConcurrencyException e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, e.getMessage());
            redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
            redirectAttributes.addAttribute("id", command.getId());

            return new ModelAndView("redirect:/user/company/edit/{id}");
        } catch (Exception e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, e.getMessage());
            return new ModelAndView("/user/company/edit", "command", command)
                    .addObject(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        }

        logger.info("修改company(公司)用户成功id=[" + company.getId() + "],时间[" + new Date() + "]");
        alertMessage = new AlertMessage(this.getMessage("default.edit.success.message", null, locale));
        redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        redirectAttributes.addAttribute("id", company.getId());

        return new ModelAndView("redirect:/user/company/show/{id}");
    }

}
