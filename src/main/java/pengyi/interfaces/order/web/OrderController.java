package pengyi.interfaces.order.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pengyi.application.order.IOrderAppService;
import pengyi.application.order.IOrderWayPointAppService;
import pengyi.application.order.command.ListOrderCommand;
import pengyi.application.order.representation.OrderRepresentation;
import pengyi.application.order.representation.OrderWayPointRepresentation;
import pengyi.interfaces.shared.web.AlertMessage;
import pengyi.interfaces.shared.web.BaseController;

import java.util.List;
import java.util.Locale;

/**
 * Created by YJH on 2016/3/14.
 */
@Controller
@RequestMapping("/order")
public class OrderController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IOrderAppService orderAppService;

    @Autowired
    private IOrderWayPointAppService orderWayPointAppService;

    @RequestMapping(value = "/list")
    public ModelAndView list(ListOrderCommand command) {
        return new ModelAndView("/order/list", "pagination", orderAppService.pagination(command))
                .addObject("command", command);
    }

    @RequestMapping(value = "/show/{id}")
    public ModelAndView show(@PathVariable("id") String id, RedirectAttributes redirectAttributes, Locale locale) {
        AlertMessage alertMessage;
        OrderRepresentation order = null;

        try {
            order = orderAppService.show(id);
        } catch (Exception e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, e.getMessage());
            redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
            return new ModelAndView("redirect:/order/list").addObject(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        }
        return new ModelAndView("/order/show", "order", order);
    }

    @RequestMapping(value = "/way/{id}")
    public ModelAndView way(@PathVariable("id") String id, RedirectAttributes redirectAttributes, Locale locale) {
        AlertMessage alertMessage;
        List<OrderWayPointRepresentation> wayPoints = null;

        try {
            wayPoints = orderWayPointAppService.list(id);
        } catch (Exception e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, e.getMessage());
            redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
            return new ModelAndView("redirect:/order/list");
        }

        if (null == wayPoints || wayPoints.size() < 2) {
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING,
                    this.getMessage("order.way.point.size.message", null, locale));
            redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
            return new ModelAndView("redirect:/order/list").addObject(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        }
        return new ModelAndView("/order/way", "wayPoints", wayPoints);
    }
}
