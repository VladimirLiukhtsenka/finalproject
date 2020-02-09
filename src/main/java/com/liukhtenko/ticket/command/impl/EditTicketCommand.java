package com.liukhtenko.ticket.command.impl;

import com.liukhtenko.ticket.command.*;
import com.liukhtenko.ticket.dao.ColumnName;
import com.liukhtenko.ticket.entity.Event;
import com.liukhtenko.ticket.entity.Ticket;
import com.liukhtenko.ticket.entity.User;
import com.liukhtenko.ticket.exception.ServiceException;
import com.liukhtenko.ticket.service.impl.EventService;
import com.liukhtenko.ticket.service.impl.TicketService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class EditTicketCommand extends Command {
    static Logger logger = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request) {
        User user = CommandHelper.findUserInSession(request);
        if (user.getRoleID() != FormParameterName.ADMIN_ID || user == null) {
            return PagePath.PAGE_LOGIN;
        }
        String page;
        TicketService ticketService = new TicketService();
        EventService eventService = new EventService();
        try {
            long id = Long.parseLong(request.getParameter(ColumnName.ID));
            HttpSession session = request.getSession();
            session.setAttribute(ColumnName.EVENT_ID, id);
            List<Ticket> tickets = ticketService.findTicketsByEventId(id);
            request.setAttribute(FormParameterName.FORM_PARAM_TICKETS, tickets);
            request.setAttribute(PageMessage.MESSAGE, "All tickets"); // FIXME: 09.02.2020
            Event event = eventService.findEventById(id);
            session.setAttribute(FormParameterName.FORM_PARAM_EVENT_NAME, event.getName());
        } catch (ServiceException e) {
            HttpSession session = request.getSession();
            session.setAttribute(PageMessage.MESSAGE_ERROR, e.toString()); // FIXME: 27.01.2020 нормальную вальдац
            page = PagePath.PAGE_ERROR;
            return page;
        }
        return PagePath.PAGE_EDIT_TICKET; // FIXME: 02.02.2020
    }
}