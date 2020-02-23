package com.liukhtenko.ticket.command.viewcommand;

import com.liukhtenko.ticket.command.Command;
import com.liukhtenko.ticket.command.FormParameterName;
import com.liukhtenko.ticket.command.PageMessage;
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

public class    ViewTicketCommand implements Command {
    static Logger logger = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request) {
        String page;
         HttpSession session = request.getSession();
        request.setAttribute(FormParameterName.TYPE_METHOD,FormParameterName.GET);
        TicketService ticketService = new TicketService();
        EventService eventService = new EventService();
        try {
            long id = Long.parseLong(request.getParameter(ColumnName.ID));
            session = request.getSession();
            session.setAttribute(ColumnName.TICKET_ID, id);
            List<Ticket> tickets = ticketService.findTicketsByEventId(id);
            session.setAttribute(FormParameterName.FORM_PARAM_TICKETS, tickets);
            Event event = eventService.findEventById(id);
            List<Integer> remTickets = new ArrayList<>();
            for (Ticket ticket : tickets) {
                int rem = ticketService.numberTicketsRemaining(ticket.getId());
                remTickets.add(rem);
            }
            session.setAttribute(FormParameterName.FORM_PARAM_REMAINING_TICKETS, remTickets);
            int end = remTickets.size() > 0 ? remTickets.size() - 1 : 0;
            session.setAttribute(FormParameterName.FORM_PARAM_END, end);
            session.setAttribute(FormParameterName.FORM_PARAM_EVENT_NAME, event.getName());
        } catch (ServiceException e) {
            session = request.getSession();
            session.setAttribute(PageMessage.MESSAGE_ERROR, "Impossible to see tickets");
            page = PagePath.PAGE_ERROR;
            logger.log(Level.WARN, "Error in ViewTicketCommand", e);
            return page;
        }
        return PagePath.PAGE_VIEW_TICKET;
    }
}
