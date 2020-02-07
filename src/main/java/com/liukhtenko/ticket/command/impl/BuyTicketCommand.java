package com.liukhtenko.ticket.command.impl;

import com.liukhtenko.ticket.command.*;
import com.liukhtenko.ticket.entity.User;
import com.liukhtenko.ticket.exception.ServiceException;
import com.liukhtenko.ticket.service.impl.TicketService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class BuyTicketCommand extends Command {
    static Logger logger = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request) {
        User user = CommandHelper.findUserInSession(request);
        if (user.getRoleID() != FormParameterName.USER_ID || user == null) {
            return PagePath.PAGE_LOGIN;
        }
        String page;
        TicketService ticketService = new TicketService();
        try {
            long userId = user.getId();
            long ticketId = Long.parseLong(request.getParameter("ticketId"));
            ticketService.buyTicket(userId, ticketId);
            List<List<String>> userTickets = ticketService.printTickets(userId);
            request.setAttribute("userTickets", userTickets);
            page = PagePath.PAGE_PROFILE;
            request.setAttribute(PageMessage.MESSAGE, "You have successfully bought a ticket!");
        } catch (NumberFormatException | ServiceException e) {
            logger.log(Level.DEBUG, "Impossible to buy ticket for" + user, e);
            request.setAttribute(PageMessage.MESSAGE_ERROR, "Unfortunately it is impossible to buy a ticket");
            page = PagePath.PAGE_ERROR;
        }
        return page;
    }
}
