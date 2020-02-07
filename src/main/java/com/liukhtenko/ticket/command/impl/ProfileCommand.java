package com.liukhtenko.ticket.command.impl;

import com.liukhtenko.ticket.command.*;
import com.liukhtenko.ticket.entity.User;
import com.liukhtenko.ticket.exception.ServiceException;
import com.liukhtenko.ticket.service.impl.TicketService;
import com.liukhtenko.ticket.validator.FormValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ProfileCommand extends Command {
    static Logger logger = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        User user = CommandHelper.findUserInSession(request);
        if (!FormValidator.isPost(request) && user.getRoleID() == FormParameterName.USER_ID) {
            TicketService ticketService = new TicketService();
            try {
                long userId = user.getId();
                List<List<String>> userTickets = ticketService.printTickets(userId);
                request.setAttribute("userTickets", userTickets);
                page = PagePath.PAGE_PROFILE;
                request.setAttribute(PageMessage.MESSAGE, "Have a great shopping!");
            } catch (NumberFormatException | ServiceException e) {
                logger.log(Level.DEBUG, "Impossible to find ticket fot" + user, e);
                request.setAttribute(PageMessage.MESSAGE_ERROR, "Unfortunately it is impossible to buy a ticket");
                page = PagePath.PAGE_ERROR;
            }
            page = PagePath.PAGE_PROFILE;
            return page;
        } else page = PagePath.PAGE_LOGIN;
        if (FormValidator.isPost(request) && request.getParameter("logout") != null) {
            HttpSession session = request.getSession();
            session.invalidate();
            page = PagePath.PAGE_LOGIN;
            return page;
        }
        return page;
    }
}
