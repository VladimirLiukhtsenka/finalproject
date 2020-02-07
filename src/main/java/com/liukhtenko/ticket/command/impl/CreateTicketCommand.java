package com.liukhtenko.ticket.command.impl;

import com.liukhtenko.ticket.command.*;
import com.liukhtenko.ticket.dao.impl.EventDao;
import com.liukhtenko.ticket.entity.*;
import com.liukhtenko.ticket.exception.ServiceException;
import com.liukhtenko.ticket.service.impl.EventService;
import com.liukhtenko.ticket.service.impl.TicketService;
import com.liukhtenko.ticket.validator.FormValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.Date;

public class CreateTicketCommand extends Command {
    static Logger logger = LogManager.getLogger();
    @Override
    public String execute(HttpServletRequest request) {
        User user = CommandHelper.findUserInSession(request);
        if (user == null) {
            return PagePath.PAGE_LOGIN;
        } else if (user.getRoleID() != FormParameterName.ADMIN_ID) {
            return PagePath.PAGE_ERROR;
        }
        String page = null;
        if (!FormValidator.isPost(request)
                || (FormValidator.isPost(request) && request.getParameter("addTicket") != null))
        {
            page = PagePath.PAGE_CREATE_TICKET;
        } else {
            TicketService ticketService = new TicketService();
            Ticket ticket = new Ticket();
            try {
                long eventId = (long)request.getSession().getAttribute("eventId");
                ticket.setEventId(eventId);
                ticket.setId(1);
                TypeSeat typeSeat = TypeSeat.findByType(request.getParameter("typeSeat")); // FIXME: 06.02.2020 Add Validation
                ticket.setTypeSeat(typeSeat);
                double price = Double.parseDouble(request.getParameter("price"));
                ticket.setPrice(price);
                int numberOfTickets = Integer.parseInt(request.getParameter("numberOfTickets"));
                ticket.setNumberOfTickets(numberOfTickets);
                ticketService.createTicket(ticket);
                page = PagePath.PAGE_EDIT_TICKET;
                request.setAttribute(PageMessage.MESSAGE_ERROR, "function"); // FIXME: 02.02.2020
            } catch (ServiceException e) {
                request.setAttribute(PageMessage.MESSAGE_ERROR, e.toString()); // FIXME: 02.02.2020
                return PagePath.PAGE_CREATE_TICKET; // FIXME: 02.02.2020
            }
        }

        return page;
    }
}
