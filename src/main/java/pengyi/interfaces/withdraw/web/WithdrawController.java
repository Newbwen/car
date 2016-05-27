package pengyi.interfaces.withdraw.web;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pengyi.application.withdraw.IWithdrawAppService;
import pengyi.application.withdraw.command.EditWithdrawCommand;
import pengyi.application.withdraw.command.ListWithdrawCommand;
import pengyi.application.withdraw.representation.WithdrawRepresentation;
import pengyi.core.commons.Constants;
import pengyi.core.util.CoreDateUtils;
import pengyi.domain.model.user.BaseUser;
import pengyi.interfaces.shared.web.AlertMessage;
import pengyi.interfaces.shared.web.BaseController;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

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

    @RequestMapping("/export_excel")
    public void exportExcel(ListWithdrawCommand command, HttpServletResponse response) {
        List<WithdrawRepresentation> representations = withdrawAppService.exportExcel(command);
        response.setContentType("application/vnd.ms-excel");
        String codedFileName;
        OutputStream fOut = null;

        try {
            codedFileName = java.net.URLEncoder.encode("体现" + CoreDateUtils.formatDateTime(new Date()), "UTF-8");
            response.setHeader("content-disposition", "attachment;filename=" + codedFileName + ".xls");
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet();

            HSSFRow rowTitle = sheet.createRow(0);//创建一行
            HSSFCell cellTitle = rowTitle.createCell(0);//创建一列
            cellTitle.setCellType(HSSFCell.CELL_TYPE_STRING);
            cellTitle.setCellValue("提现人");
            HSSFCell cellTitle1 = rowTitle.createCell(1);
            cellTitle1.setCellType(HSSFCell.CELL_TYPE_STRING);
            cellTitle1.setCellValue("申请时间");
            HSSFCell cellTitle2 = rowTitle.createCell(2);
            cellTitle2.setCellType(HSSFCell.CELL_TYPE_STRING);
            cellTitle2.setCellValue("提现金额");
            HSSFCell cellTitle3 = rowTitle.createCell(3);
            cellTitle3.setCellType(HSSFCell.CELL_TYPE_STRING);
            cellTitle3.setCellValue("提现状态");
            HSSFCell cellTitle5 = rowTitle.createCell(5);
            cellTitle5.setCellType(HSSFCell.CELL_TYPE_STRING);
            cellTitle5.setCellValue("结束时间");

            for (int i = 1; i < representations.size(); i++) {
                WithdrawRepresentation representation = representations.get(i - 1);
                HSSFRow row = sheet.createRow(i);//创建一行
                HSSFCell cell = row.createCell(0);//创建一列
                cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                cell.setCellValue(representation.getUsername());
                HSSFCell cell1 = row.createCell(1);//
                cell1.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
                cell1.setCellValue(representation.getMoney() == null ? null : representation.getMoney().toString());
                HSSFCell cell2 = row.createCell(2);//
                cell2.setCellType(HSSFCell.CELL_TYPE_STRING);
                cell2.setCellValue(representation.getStatus().getName()==null ? null : representation.getStatus().getName());
                HSSFCell cell3 = row.createCell(3);//
                cell3.setCellType(HSSFCell.CELL_TYPE_STRING);
                cell3.setCellValue(representation.getFinishTime() == null? "" :representation.getFinishTime().toString());
            }
            fOut = response.getOutputStream();
            workbook.write(fOut);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fOut.flush();
                fOut.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

}
