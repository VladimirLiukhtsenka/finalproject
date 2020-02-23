package com.liukhtenko.ticket.command.usercommand;

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

public class ProfileCommand implements Command {
    static Logger logger = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        User user = CommandHelper.findUserInSession(request);
         HttpSession session = request.getSession();
        request.setAttribute(FormParameterName.TYPE_METHOD,FormParameterName.GET);
        if (!FormValidator.isPost(request)) {
            TicketService ticketService = new TicketService();
            try {
                long userId = user.getId();
                List<List<String>> userTickets = ticketService.printTickets(userId);
                session.setAttribute(FormParameterName.FORM_PARAM_USER_TICKETS, userTickets);
                session.setAttribute(FormParameterName.FORM_PARAM_TICKET_SIZE, userTickets.size());
                page = PagePath.PAGE_PROFILE;
            } catch (NumberFormatException | ServiceException e) {
                logger.log(Level.DEBUG, "Impossible to find ticket fot" + user, e);
                request.setAttribute(PageMessage.MESSAGE_ERROR, "Unfortunately it is impossible to buy a ticket");
                page = PagePath.PAGE_ERROR;
            }
            return page;
        }
        if (FormValidator.isPost(request) && request.getParameter(FormParameterName.FORM_PARAM_LOGOUT) != null) {
            session = request.getSession();
            session.invalidate();
          //  request.setAttribute(FormParameterName.TYPE_METHOD,FormParameterName.GET);
            page = PagePath.PAGE_LOGIN;
            return page;
        }
        return page;
    }
}
