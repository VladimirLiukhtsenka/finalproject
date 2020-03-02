package com.liukhtenko.ticket.command.admincommand;

import com.liukhtenko.ticket.command.Command;
import com.liukhtenko.ticket.command.FormParameterName;
import com.liukhtenko.ticket.command.PagePath;
import com.liukhtenko.ticket.dao.ColumnName;
import com.liukhtenko.ticket.entity.Ticket;
import com.liukhtenko.ticket.entity.TypeSeat;
import com.liukhtenko.ticket.exception.ServiceException;
import com.liukhtenko.ticket.service.impl.TicketService;
import com.liukhtenko.ticket.validator.FormValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * The class that create ticket.
 *
 * @author Vladimir Liukhtenko
 * @version 1.25 02 Feb 2020
 */
public class CreateTicketCommand implements Command {
    private static Logger logger = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        request.setAttribute(FormParameterName.TYPE_METHOD, FormParameterName.GET);
        if (!FormValidator.isPost(request)
                || (FormValidator.isPost(request) && request.getParameter(FormParameterName.FORM_PARAM_ADD_TICKET) != null)) {
            page = PagePath.PAGE_CREATE_TICKET;
        } else {
            TicketService ticketService = new TicketService();
            Ticket ticket = new Ticket();
            try {
                long eventId = (long) request.getSession().getAttribute(ColumnName.EVENT_ID);
                ticket.setEventId(eventId);
                TypeSeat typeSeat = TypeSeat.findByType(request.getParameter(ColumnName.TYPE_SEAT));
                ticket.setTypeSeat(typeSeat);
                String price = request.getParameter(ColumnName.PRICE);
                if (FormValidator.isValidPrice(price)) {
                    double realPrice = Double.parseDouble(price);
                    ticket.setPrice(realPrice);
                }
                String numberOfTickets = request.getParameter(ColumnName.NUMBER_OF_TICKETS);
                if (FormValidator.isValidNumber(numberOfTickets)) {
                    int realNumberOfTickets = Integer.parseInt(numberOfTickets);
                    ticket.setNumberOfTickets(realNumberOfTickets);
                }
                ticketService.createTicket(ticket);
                List<Ticket> tickets = ticketService.findTicketsByEventId(eventId);
                HttpSession session = request.getSession();
                session.setAttribute(FormParameterName.FORM_PARAM_TICKETS, tickets);
                request.setAttribute(FormParameterName.TYPE_METHOD, FormParameterName.POST);
                page = PagePath.PAGE_EDIT_TICKET;
            } catch (ServiceException e) {
                logger.log(Level.ERROR, "Error in CreateTicketCommand", e);
                request.setAttribute(FormParameterName.FORM_PARAM_MESSAGE_ERROR, "Impossible create ticket.");
                return PagePath.PAGE_CREATE_TICKET;
            }
        }
        return page;
    }
}
