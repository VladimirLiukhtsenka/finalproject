package com.liukhtenko.ticket.command.admincommand;

import com.liukhtenko.ticket.command.Command;
import com.liukhtenko.ticket.command.FormParameterName;
import com.liukhtenko.ticket.command.PagePath;
import com.liukhtenko.ticket.dao.ColumnName;
import com.liukhtenko.ticket.exception.ServiceException;
import com.liukhtenko.ticket.service.impl.TicketOfficeService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * The class that delete ticket office.
 *
 * @author Vladimir Liukhtenko
 * @version 1.25 02 Feb 2020
 */
public class DeleteTicketOfficeCommand implements Command {
    private static Logger logger = LogManager.getLogger();

    /**
     * @param request from browser
     * @return String page
     */
    @Override
    public String execute(HttpServletRequest request) {
        String page = PagePath.PAGE_EDIT_TICKET_OFFICE;
        request.setAttribute(FormParameterName.TYPE_METHOD, FormParameterName.GET);
        if (request.getParameter(FormParameterName.FORM_PARAM_DELETE_TICKET_OFFICE) != null) {
            TicketOfficeService service = new TicketOfficeService();
            String phone = ColumnName.PHONE;
            if (request.getParameter(ColumnName.PHONE) != null) {
                phone = request.getParameter(ColumnName.PHONE);
            }
            try {
                service.deleteTicketOfficeByPhone(phone);
                request.setAttribute(FormParameterName.TYPE_METHOD, FormParameterName.POST);
            } catch (ServiceException e) {
                request.setAttribute(FormParameterName.FORM_PARAM_MESSAGE_ERROR, "Unable to delete ticket office");
                logger.log(Level.ERROR, "Error in DeleteTicketOfficeCommand", e);
                page = PagePath.PAGE_ERROR;
            }
        }
        return page;
    }
}
