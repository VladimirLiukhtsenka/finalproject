package com.liukhtenko.ticket.command.impl;

import com.liukhtenko.ticket.command.Command;
import com.liukhtenko.ticket.command.FormParameterName;
import com.liukhtenko.ticket.command.PageMessage;
import com.liukhtenko.ticket.command.PagePath;
import com.liukhtenko.ticket.dao.ColumnName;
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
                if (FormValidator.isValidString(request.getParameter(ColumnName.MAIL), FormRegexValidator.EMAIL)) {
                    mail = request.getParameter(ColumnName.MAIL);
                }
                String password = FormParameterName.DEFAULT_VALUE;
                if (FormValidator.isValidString(request.getParameter(ColumnName.PASSWORD), FormRegexValidator.PASSWORD)) {
                    password = request.getParameter(ColumnName.PASSWORD);
                }
                TicketService ticketService = new TicketService();
                UserService userService = new UserService();
                User user = userService.findUserByMailAndPassword(mail, password);
                HttpSession session = request.getSession();
                session.setAttribute(FormParameterName.FORM_PARAM_USER, user);
                session.setAttribute(FormParameterName.FORM_PARAM_IS_ADMIN, false);
                if (user.getRoleID() == FormParameterName.ADMIN_ID) {
                    session.setAttribute(FormParameterName.FORM_PARAM_IS_ADMIN, true);
                    page = PagePath.PAGE_ADMIN_PROFILE;
                } else {
                    session.setAttribute(FormParameterName.FORM_PARAM_IS_USER, true);
                    page = PagePath.PAGE_PROFILE;
                    long userId = user.getId();
                    List<List<String>> userTickets = ticketService.printTickets(userId);
                    request.setAttribute(FormParameterName.FORM_PARAM_USER_TICKETS, userTickets);
                    request.setAttribute(PageMessage.MESSAGE, "Have a great shopping!");
                }
            } catch (ServiceException e) {
                logger.log(Level.INFO, "incorrect data");
                request.setAttribute(PageMessage.MESSAGE_ERROR, "incorrect data" + e.toString()); // FIXME: 08.02.2020
                page = PagePath.PAGE_LOGIN;
            }
        }
        return page;
    }
}