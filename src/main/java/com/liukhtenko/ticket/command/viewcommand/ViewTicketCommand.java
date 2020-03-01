package com.liukhtenko.ticket.command.viewcommand;

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
import java.util.ArrayList;
import java.util.List;

/**
 * The class allows to view tickets for the selected event.
 *
 * @author Vladimir Liukhtenko
 * @version 1.25 02 Feb 2020
 */
public class ViewTicketCommand implements Command {
    private static Logger logger = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        HttpSession session = request.getSession();
        request.setAttribute(FormParameterName.TYPE_METHOD, FormParameterName.GET);
        TicketService ticketService = new TicketService();
        EventService eventService = new EventService();
        long id = 0;
        try {
            if (request.getParameter(ColumnName.ID) != null) {
                id = Long.parseLong(request.getParameter(ColumnName.ID));
            }
            session.setAttribute(ColumnName.TICKET_ID, id);
            List<Ticket> tickets = ticketService.findTicketsByEventId(id);
            session.setAttribute(FormParameterName.FORM_PARAM_TICKETS, tickets);
            Event event = eventService.findEventById(id);
            List<Integer> remainTickets = new ArrayList<>();
            for (Ticket ticket : tickets) {
                int rem = TicketService.numberTicketsRemaining(ticket.getId());
                remainTickets.add(rem);
            }
            session.setAttribute(FormParameterName.FORM_PARAM_REMAINING_TICKETS, remainTickets);
            int end = remainTickets.size() > 0 ? remainTickets.size() - 1 : 0;
            session.setAttribute(FormParameterName.FORM_PARAM_END, end);
            session.setAttribute(FormParameterName.FORM_PARAM_EVENT_NAME, event.getName());
        } catch (ServiceException e) {
            session = request.getSession();
            session.setAttribute(FormParameterName.FORM_PARAM_MESSAGE_ERROR, "Impossible to see tickets");
            page = PagePath.PAGE_ERROR;
            logger.log(Level.WARN, "Error in ViewTicketCommand", e);
            return page;
        }
        return PagePath.PAGE_VIEW_TICKET;
    }
}
