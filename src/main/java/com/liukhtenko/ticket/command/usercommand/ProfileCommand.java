package com.liukhtenko.ticket.command.usercommand;

import com.liukhtenko.ticket.command.Command;
import com.liukhtenko.ticket.command.CommandHelper;
import com.liukhtenko.ticket.command.FormParameterName;
import com.liukhtenko.ticket.command.PagePath;
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

/**
 * The class that displays the user profile page.
 *
 * @author Vladimir Liukhtenko
 * @version 1.25 02 Feb 2020
 */
public class ProfileCommand implements Command {
    private static Logger logger = LogManager.getLogger();
    /**
     *
     * @param request from browser
     * @return String page
     *
     */
    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        User user = CommandHelper.findUserInSession(request);
        HttpSession session = request.getSession();
        request.setAttribute(FormParameterName.TYPE_METHOD, FormParameterName.GET);
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
                request.setAttribute(FormParameterName.FORM_PARAM_MESSAGE_ERROR, "Unfortunately it is impossible to buy a ticket");
                page = PagePath.PAGE_ERROR;
            }
        }
        if (FormValidator.isPost(request) && request.getParameter(FormParameterName.FORM_PARAM_LOGOUT) != null) {
            session = request.getSession();
            session.invalidate();
            page = PagePath.PAGE_LOGIN;
        }
        return page;
    }
}
