package com.liukhtenko.ticket.command.admincommand;

import com.liukhtenko.ticket.command.Command;
import com.liukhtenko.ticket.command.FormParameterName;
import com.liukhtenko.ticket.command.PagePath;
import com.liukhtenko.ticket.dao.ColumnName;
import com.liukhtenko.ticket.exception.ServiceException;
import com.liukhtenko.ticket.service.impl.TicketService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * The class that delete ticket.
 *
 * @author Vladimir Liukhtenko
 * @version 1.25 02 Feb 2020
 */
public class DeleteTicketCommand implements Command {
    private static Logger logger = LogManager.getLogger();
    /**
     *
     * @param request from browser
     * @return String page
     *
     */
    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        request.setAttribute(FormParameterName.TYPE_METHOD, FormParameterName.GET);
        if (request.getParameter(FormParameterName.FORM_PARAM_DELETE_TICKET) != null) {
            TicketService ticketService = new TicketService();
            long id = 0;
            if (request.getParameter(ColumnName.ID) != null) {
                id = Long.parseLong(request.getParameter(ColumnName.ID));
            }
            try {
                ticketService.deleteTicketById(id);
                request.setAttribute(FormParameterName.TYPE_METHOD, FormParameterName.POST);
            } catch (ServiceException e) {
                request.setAttribute(FormParameterName.FORM_PARAM_MESSAGE_ERROR, "Unable to delete ticket");
                logger.log(Level.ERROR, "Error in DeleteTicketCommand", e);
                return PagePath.PAGE_ERROR;
            }
            page = PagePath.PAGE_EDIT_TICKET;
        }
        return page;
    }
}
