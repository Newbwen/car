package pengyi.interfaces.user.driver.web;

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
import pengyi.application.user.driver.IDriverAppService;
import pengyi.application.user.driver.command.CreateDriverCommand;
import pengyi.application.user.driver.command.EditDriverCommand;
import pengyi.application.user.driver.command.BaseListDriverCommand;
import pengyi.application.user.driver.representation.DriverRepresentation;
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
@RequestMapping("/user/driver")
public class DriverController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IDriverAppService driverAppService;

    @RequestMapping(value = "/list")
    public ModelAndView list(BaseListDriverCommand command) {
        return new ModelAndView("/user/driver/list", "command", command)
                .addObject("pagination", driverAppService.pagination(command));
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView create(@ModelAttribute("command") CreateDriverCommand command) {
        return new ModelAndView("/user/driver/create", "command", command);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ModelAndView create(@Valid @ModelAttribute("command") CreateDriverCommand command,
                               BindingResult bindingResult, RedirectAttributes redirectAttributes,
                               Locale locale) {

        if (bindingResult.hasErrors()) {
            return new ModelAndView("/user/driver/create", "command", command);
        }

        AlertMessage alertMessage = null;

        DriverRepresentation driver = null;

        try {
            driver = driverAppService.create(command);
        } catch (Exception e) {
            logger.error(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, e.getMessage());
            return new ModelAndView("/user/driver/create", "command", command)
                    .addObject(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        }

        logger.info("创建driver(司机)用户成功id=[" + driver.getId() + "],时间[" + new Date() + "]");

        alertMessage = new AlertMessage(this.getMessage("default.create.success.message", null, locale));
        redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        redirectAttributes.addAttribute("id", driver.getId());
        return new ModelAndView("redirect:/user/driver/show/{id}");
    }

    @RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
    public ModelAndView show(@PathVariable String id, RedirectAttributes redirectAttributes, Locale locale) {
        AlertMessage alertMessage;
        DriverRepresentation driver = null;
        try {
            driver = driverAppService.show(id);
        } catch (Exception e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, e.getMessage());
            redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
            return new ModelAndView("redirect:/user/driver/list");
        }
        return new ModelAndView("/user/driver/show", "driver", driver);
    }

    @RequestMapping(value = "/edit/{id}")
    public ModelAndView edit(@PathVariable String id, @ModelAttribute("command") EditDriverCommand command,
                             RedirectAttributes redirectAttributes, Locale locale) {

        AlertMessage alertMessage;

        DriverRepresentation driver = null;

        try {
            driver = driverAppService.show(id);
        } catch (Exception e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, e.getMessage());
            redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
            return new ModelAndView("redirect:/user/driver/list");
        }

        return new ModelAndView("/user/driver/edit", "command", command).addObject("driver", driver);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ModelAndView edit(@Valid @ModelAttribute("command") EditDriverCommand command, BindingResult bindingResult,
                             RedirectAttributes redirectAttributes, Locale locale) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("/user/driver/edit", "command", command);
        }

        AlertMessage alertMessage;
        DriverRepresentation driver = null;

        try {
            driver = driverAppService.edit(command);
        } catch (ConcurrencyException e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, e.getMessage());
            redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
            redirectAttributes.addAttribute("id", command.getId());

            return new ModelAndView("redirect:/user/driver/edit/{id}");
        } catch (Exception e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, e.getMessage());
            return new ModelAndView("/user/driver/edit", "command", command)
                    .addObject(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        }

        logger.info("修改driver(司机)用户成功id=[" + driver.getId() + "],时间[" + new Date() + "]");
        alertMessage = new AlertMessage(this.getMessage("default.edit.success.message", null, locale));
        redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        redirectAttributes.addAttribute("id", driver.getId());

        return new ModelAndView("redirect:/user/driver/show/{id}");
    }

}
