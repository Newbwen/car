package pengyi.interfaces.message.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pengyi.application.message.IMessageAppService;
import pengyi.application.message.command.CreateMessageByRoleCommand;
import pengyi.application.message.command.ListMessageCommand;
import pengyi.application.message.representation.MessageRepresentation;
import pengyi.application.role.IRoleAppService;
import pengyi.core.exception.ConcurrencyException;
import pengyi.interfaces.shared.web.AlertMessage;
import pengyi.interfaces.shared.web.BaseController;
import pengyi.interfaces.shared.web.JsonMessage;

import javax.validation.Valid;
import java.util.Date;
import java.util.Locale;

/**
 * Created by liubowen on 2016/3/11.
 */
@Controller
@RequestMapping("/message")
public class MessageController extends BaseController {
    //日志
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired//注入appService
    private IMessageAppService messageAppService;

    @Autowired
    private IRoleAppService roleAppService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView list(ListMessageCommand command) {
        return new ModelAndView("message/list", "command", command)
                .addObject("pagination", messageAppService.pagination(command));
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView create(@ModelAttribute("command") CreateMessageByRoleCommand command) {
        return new ModelAndView("message/create", "command", command);
    }

    public JsonMessage roleList() {
        JsonMessage jsonMessage = new JsonMessage();
        try {
            jsonMessage.setData(roleAppService.roleList());
            jsonMessage.setCode("0");
            jsonMessage.setMessage("查询成功");
        } catch (Exception e) {
            jsonMessage.setData(null);
            jsonMessage.setCode("1");
            jsonMessage.setMessage("查询失败");
        }
        return jsonMessage;
    }
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ModelAndView create(@Valid @ModelAttribute("command") CreateMessageByRoleCommand command,
                               BindingResult bindingResult, RedirectAttributes redirectAttributes,
                               Locale locale) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("/message/create", "command", command);
        }
        AlertMessage alertMessage = null;

        try {
            messageAppService.create(command);
        } catch (Exception e) {
            logger.error(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, e.getMessage());
            return new ModelAndView("/message/create", "command", command).addObject(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        }
        logger.info("创建站内信息成功,时间[" + new Date() + "]");
        alertMessage = new AlertMessage(this.getMessage("default.create.success.message", null, locale));
        redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        return new ModelAndView("redirect:/message/show/{id}");
    }

    @RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
    public ModelAndView show(@PathVariable String id, RedirectAttributes redirectAttributes, Locale locale) {
        AlertMessage alertMessage;
        MessageRepresentation representation;
        try {
            representation = messageAppService.show(id);
        } catch (Exception e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, e.getMessage());
            redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
            return new ModelAndView("redirect:message/list");
        }
        return new ModelAndView("/message/show", "message", representation);
    }
    @RequestMapping(value="/delete/{id}",method = RequestMethod.GET)
    public ModelAndView delete(@Valid @ModelAttribute("command") String id,
                               BindingResult bindingResult, RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors()){
            return new ModelAndView("message/list");
        }
        AlertMessage alertMessage;
        MessageRepresentation representation=null;
        try{
            representation=messageAppService.delete(id);
        }catch (ConcurrencyException e){
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING,e.getMessage());
            redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
            redirectAttributes.addAttribute("id", id);
            return new ModelAndView("redirect:/message/list");
        }catch (Exception e){
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, e.getMessage());
            return new ModelAndView("/message/list")
                    .addObject(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        }
        logger.info("id=[" + id + "],时间[" + new Date() + "]");
        alertMessage = new AlertMessage(this.getMessage("default.edit.success.message",null,null));
        redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        redirectAttributes.addAttribute("id",id);

        return new ModelAndView("redirect:/message/list");

    }



}
