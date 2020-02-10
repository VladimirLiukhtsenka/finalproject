package com.liukhtenko.ticket.command.impl;

import com.liukhtenko.ticket.command.*;
import com.liukhtenko.ticket.dao.ColumnName;
import com.liukhtenko.ticket.entity.Ticket;
import com.liukhtenko.ticket.entity.TypeSeat;
import com.liukhtenko.ticket.entity.User;
import com.liukhtenko.ticket.exception.ServiceException;
import com.liukhtenko.ticket.service.impl.TicketService;
import com.liukhtenko.ticket.validator.FormValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class CreateTicketCommand extends Command {
    static Logger logger = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request) {
        User user = CommandHelper.findUserInSession(request);
        String page;
        if (!FormValidator.isPost(request)
                || (FormValidator.isPost(request) && request.getParameter(FormParameterName.FORM_PARAM_ADD_TICKET) != null)) {
            page = PagePath.PAGE_CREATE_TICKET;
        } else {
            TicketService ticketService = new TicketService();
            Ticket ticket = new Ticket();
            try {
                long eventId = (long) request.getSession().getAttribute(ColumnName.EVENT_ID);
                ticket.setEventId(eventId);
                //  ticket.setId(1); FIXME: 08.02.2020 возможно не нужен
                TypeSeat typeSeat = TypeSeat.findByType(request.getParameter(ColumnName.TYPE_SEAT)); // FIXME: 06.02.2020 Add Validation
                ticket.setTypeSeat(typeSeat);
                double price = Double.parseDouble(request.getParameter(ColumnName.PRICE));
                ticket.setPrice(price);
                int numberOfTickets = Integer.parseInt(request.getParameter(ColumnName.NUMBER_OF_TICKETS));
                ticket.setNumberOfTickets(numberOfTickets);
                ticketService.createTicket(ticket);
                page = PagePath.PAGE_EDIT_TICKET;
                request.setAttribute(PageMessage.MESSAGE, "tickets created successfully"); // FIXME: 02.02.2020
            } catch (ServiceException e) {
                request.setAttribute(PageMessage.MESSAGE_ERROR, e.toString()); // FIXME: 02.02.2020
                return PagePath.PAGE_CREATE_TICKET; // FIXME: 02.02.2020
            }
        }

        return page;
    }
}
