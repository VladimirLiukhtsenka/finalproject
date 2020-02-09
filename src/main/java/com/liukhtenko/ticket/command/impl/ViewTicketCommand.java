package com.liukhtenko.ticket.command.impl;

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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class ViewTicketCommand extends Command {
    static Logger logger = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        TicketService ticketService = new TicketService();
        EventService eventService = new EventService();
        try {
            long id = Long.parseLong(request.getParameter(ColumnName.ID));
            HttpSession session = request.getSession();
            session.setAttribute(ColumnName.TICKET_ID, id);
            List<Ticket> tickets = ticketService.findTicketsByEventId(id);
            request.setAttribute(FormParameterName.FORM_PARAM_TICKETS, tickets);
            request.setAttribute(PageMessage.MESSAGE, "All tickets");
            Event event = eventService.findEventById(id);
            List<Integer> remTickets = new ArrayList<>();
            for (Ticket ticket : tickets) {
                int rem = ticketService.numberTicketsRemaining(ticket.getId());
                remTickets.add(rem);
            }
            request.setAttribute(FormParameterName.FORM_PARAM_REMAINING_TICKETS, remTickets);
            int end = remTickets.size() > 0 ? remTickets.size() - 1 : 0;
            request.setAttribute(FormParameterName.FORM_PARAM_END, end);
            session.setAttribute(FormParameterName.FORM_PARAM_EVENT_NAME, event.getName());
        } catch (ServiceException e) {
            HttpSession session = request.getSession();
            session.setAttribute(PageMessage.MESSAGE_ERROR, "Impossible to see tickets" + e.toString()); // FIXME: 27.01.2020 нормальную вальдац
            page = PagePath.PAGE_ERROR;
            return page;
        }
        return PagePath.PAGE_VIEW_TICKET; // FIXME: 02.02.2020
    }
}
