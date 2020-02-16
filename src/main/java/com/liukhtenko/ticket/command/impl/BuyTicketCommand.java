package com.liukhtenko.ticket.command.impl;

import com.liukhtenko.ticket.command.*;
import com.liukhtenko.ticket.dao.ColumnName;
import com.liukhtenko.ticket.entity.User;
import com.liukhtenko.ticket.exception.ServiceException;
import com.liukhtenko.ticket.service.impl.TicketService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class BuyTicketCommand extends Command {
    static Logger logger = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request) {
        User user = CommandHelper.findUserInSession(request);
        String page;
        TicketService ticketService = new TicketService();
        try {
            long userId = user.getId();
            long ticketId = Long.parseLong(request.getParameter(ColumnName.TICKET_ID));
            ticketService.buyTicket(userId, ticketId);
            List<List<String>> userTickets = ticketService.printTickets(userId);
            HttpSession session = request.getSession();
            session.setAttribute(FormParameterName.FORM_PARAM_USER_TICKETS, userTickets);
            page = PagePath.PAGE_PROFILE;
        } catch (NumberFormatException | ServiceException e) {
            logger.log(Level.WARN, "Impossible to buy ticket for" + user, e);
            request.setAttribute(PageMessage.MESSAGE_ERROR, "Unfortunately it is impossible to buy a ticket");
            page = PagePath.PAGE_ERROR;
        }
        return page;
    }
}
