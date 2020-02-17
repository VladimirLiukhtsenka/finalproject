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
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class EditTicketCommand extends Command {
    static Logger logger = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        TicketService ticketService = new TicketService();
        EventService eventService = new EventService();
        try {
            long id = Long.parseLong(request.getParameter(ColumnName.ID));
            HttpSession session = request.getSession();
            session.setAttribute(ColumnName.EVENT_ID, id);
            List<Ticket> tickets = ticketService.findTicketsByEventId(id);
            session.setAttribute(FormParameterName.FORM_PARAM_TICKETS, tickets);
            Event event = eventService.findEventById(id);
            session.setAttribute(FormParameterName.FORM_PARAM_EVENT_NAME, event.getName());
        } catch (ServiceException e) {
            request.setAttribute(PageMessage.MESSAGE_ERROR, "Unable to edit tickets");
            logger.log(Level.WARN, "Error in EditTicketCommand", e);
            page = PagePath.PAGE_ERROR;
            return page;
        }
        return PagePath.PAGE_EDIT_TICKET;
    }
}