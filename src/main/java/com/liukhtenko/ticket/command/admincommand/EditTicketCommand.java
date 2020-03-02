package com.liukhtenko.ticket.command.admincommand;

import com.liukhtenko.ticket.command.Command;
import com.liukhtenko.ticket.command.FormParameterName;
import com.liukhtenko.ticket.command.PagePath;
import com.liukhtenko.ticket.dao.ColumnName;
import com.liukhtenko.ticket.entity.Event;
import com.liukhtenko.ticket.entity.Ticket;
import com.liukhtenko.ticket.exception.ServiceException;
import com.liukhtenko.ticket.service.impl.EventService;
import com.liukhtenko.ticket.service.impl.TicketService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * The class that edit ticket.
 *
 * @author Vladimir Liukhtenko
 * @version 1.25 02 Feb 2020
 */
public class EditTicketCommand implements Command {
    private static Logger logger = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute(FormParameterName.TYPE_METHOD, FormParameterName.GET);
        TicketService ticketService = new TicketService();
        EventService eventService = new EventService();
        try {
            HttpSession session = request.getSession();
            long id;
            if (request.getParameter(ColumnName.ID) != null) {
                id = Long.parseLong(request.getParameter(ColumnName.ID));
                session.setAttribute(ColumnName.EVENT_ID, id);
            } else {
                id = (long) session.getAttribute(ColumnName.EVENT_ID);
            }
            List<Ticket> tickets = ticketService.findTicketsByEventId(id);
            session.setAttribute(FormParameterName.FORM_PARAM_TICKETS, tickets);
            Event event = eventService.findEventById(id);
            session.setAttribute(FormParameterName.FORM_PARAM_EVENT_NAME, event.getName());
        } catch (ServiceException e) {
            request.setAttribute(FormParameterName.FORM_PARAM_MESSAGE_ERROR, "Unable to edit tickets");
            logger.log(Level.WARN, "Error in EditTicketCommand", e);
            return PagePath.PAGE_ERROR;
        }
        return PagePath.PAGE_EDIT_TICKET;
    }
}