package com.liukhtenko.ticket.command.admincommand;

import com.liukhtenko.ticket.command.Command;
import com.liukhtenko.ticket.command.FormParameterName;
import com.liukhtenko.ticket.command.PagePath;
import com.liukhtenko.ticket.entity.TicketOffice;
import com.liukhtenko.ticket.exception.ServiceException;
import com.liukhtenko.ticket.service.impl.TicketOfficeService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * The class that edit ticket office.
 *
 * @author Vladimir Liukhtenko
 * @version 1.25 02 Feb 2020
 */
public class EditTicketOfficeCommand implements Command {
    private static Logger logger = LogManager.getLogger();

    /**
     * @param request from browser
     * @return String page
     */
    @Override
    public String execute(HttpServletRequest request) {
        String page = PagePath.PAGE_EDIT_TICKET_OFFICE;
        request.setAttribute(FormParameterName.TYPE_METHOD, FormParameterName.GET);
        TicketOfficeService ticketOfficeService = new TicketOfficeService();
        try {
            List<TicketOffice> ticketOffices = ticketOfficeService.findAllTicketOffice();
            request.setAttribute(FormParameterName.FORM_PARAM_TICKET_OFFICES, ticketOffices);
        } catch (ServiceException e) {
            logger.log(Level.WARN, "Error in EditTicketOfficeCommand", e);
            request.setAttribute(FormParameterName.FORM_PARAM_MESSAGE_ERROR, "Impossible to view ticket office.");
            page = PagePath.PAGE_ERROR;
        }
        return page;
    }
}
