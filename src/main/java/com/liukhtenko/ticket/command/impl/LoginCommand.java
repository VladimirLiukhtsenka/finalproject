package com.liukhtenko.ticket.command.impl;

import com.liukhtenko.ticket.command.Command;
import com.liukhtenko.ticket.command.FormParameterName;
import com.liukhtenko.ticket.command.PageMessage;
import com.liukhtenko.ticket.command.PagePath;
import com.liukhtenko.ticket.entity.User;
import com.liukhtenko.ticket.exception.ServiceException;
import com.liukhtenko.ticket.service.impl.TicketService;
import com.liukhtenko.ticket.service.impl.UserService;
import com.liukhtenko.ticket.validator.FormRegexValidator;
import com.liukhtenko.ticket.validator.FormValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class LoginCommand extends Command {
    static Logger logger = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        if (!FormValidator.isPost(request)) {
            page = PagePath.PAGE_LOGIN;
            return page;
        } else {
            try {
                String mail = FormParameterName.DEFAULT_VALUE;
                if (FormValidator.isValidString(request.getParameter(FormParameterName.FORM_PARAM_MAIL), FormRegexValidator.EMAIL)) {
                    mail = request.getParameter(FormParameterName.FORM_PARAM_MAIL);
                }
                String password = FormParameterName.DEFAULT_VALUE;
                if (FormValidator.isValidString(request.getParameter(FormParameterName.FORM_PARAM_PASSWORD), FormRegexValidator.PASSWORD)) {
                    password = request.getParameter(FormParameterName.FORM_PARAM_PASSWORD);
                }
                TicketService ticketService = new TicketService();
                UserService userService = new UserService();
                User user = userService.findUserByMailAndPassword(mail, password);  // FIXME: 07.02.2020 Валидацию 
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                session.setAttribute("isAdmin", false);
                if (user.getRoleID() == 1) {
                    session.setAttribute("isAdmin", true);
                    page = PagePath.PAGE_ADMIN_PROFILE;
                } else {
                    session.setAttribute("isUser", true);
                    page = PagePath.PAGE_PROFILE;
                    long userId = user.getId();
                    List<List<String>> userTickets = ticketService.printTickets(userId);
                    request.setAttribute("userTickets", userTickets);
                    request.setAttribute(PageMessage.MESSAGE, "Have a great shopping!");
                }
            } catch (ServiceException e) {
                logger.log(Level.INFO, "incorrect data", e);
                request.setAttribute(PageMessage.MESSAGE_ERROR, e.toString()); // FIXME: 08.02.2020
                page = PagePath.PAGE_LOGIN;
            }
        }
        return page;
    }
}