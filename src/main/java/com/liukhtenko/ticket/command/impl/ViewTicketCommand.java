package com.liukhtenko.ticket.command.impl;

import com.liukhtenko.ticket.command.Command;
import com.liukhtenko.ticket.command.PageMessage;
import com.liukhtenko.ticket.command.PagePath;
import com.liukhtenko.ticket.entity.Event;
import com.liukhtenko.ticket.entity.Ticket;
import com.liukhtenko.ticket.exception.ServiceException;
import com.liukhtenko.ticket.service.impl.EventService;
import com.liukhtenko.ticket.service.impl.TicketService;
import com.liukhtenko.ticket.validator.FormValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class ViewTicketCommand extends Command  {
    @Override
    public String execute(HttpServletRequest request) {
        String page;
        TicketService ticketService = new TicketService();
        EventService eventService = new EventService();
        try {
            long id = Long.parseLong( request.getParameter("id"));
            HttpSession session = request.getSession();
            session.setAttribute("eventId",id);
            List<Ticket> tickets = ticketService.findTicketsByEventId(id);
            request.setAttribute("tickets",tickets);
            request.setAttribute(PageMessage.MESSAGE_ERROR, "тут метод!");
            Event event = eventService.findEventById(id);

            List <Integer> remTickets = new ArrayList<>();
            for (Ticket ticket : tickets) {
                int rem = ticketService.numberTicketsRemaining(ticket.getId());
                remTickets.add(rem);
            }
            request.setAttribute("remTickets",remTickets);
            int end = remTickets.size();
            request.setAttribute("end",end);
            session.setAttribute("eventName",event.getName());
        } catch (ServiceException e) {
            HttpSession session = request.getSession();
            session.setAttribute(PageMessage.MESSAGE_ERROR, e.toString()); // FIXME: 27.01.2020 нормальную вальдац
            page = PagePath.PAGE_ERROR;
            return page;
        }
        return PagePath.PAGE_VIEW_TICKET; // FIXME: 02.02.2020
    }
}
