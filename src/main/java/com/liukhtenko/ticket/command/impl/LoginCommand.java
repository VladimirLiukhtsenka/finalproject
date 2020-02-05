package com.liukhtenko.ticket.command.impl;

import com.liukhtenko.ticket.command.Command;
import com.liukhtenko.ticket.command.PageMessage;
import com.liukhtenko.ticket.command.PagePath;
import com.liukhtenko.ticket.entity.User;
import com.liukhtenko.ticket.exception.ServiceException;
import com.liukhtenko.ticket.service.impl.UserService;
import com.liukhtenko.ticket.validator.FormRegexValidator;
import com.liukhtenko.ticket.validator.FormValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LoginCommand extends Command {
    static Logger logger = LogManager.getLogger();

    private static final String FORM_PARAM_PASSWORD = "password";  // FIXME: 29.01.2020 одинаклвые поля объединить
    private static final String FORM_PARAM_MAIL = "mail";
    private static final String DEFAULT_VALUE = "\"\"";

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        if (!FormValidator.isPost(request)) {
            page = PagePath.PAGE_LOGIN;
            return page;
        } else {
            try {
                String mail = DEFAULT_VALUE;
                if (FormValidator.isValidString(request.getParameter(FORM_PARAM_MAIL), FormRegexValidator.EMAIL)) {
                    mail = request.getParameter(FORM_PARAM_MAIL);
                }
                String password = DEFAULT_VALUE;
                if (FormValidator.isValidString(request.getParameter(FORM_PARAM_PASSWORD), FormRegexValidator.PASSWORD)) {
                    password = request.getParameter(FORM_PARAM_PASSWORD);
                }
                UserService userService = new UserService();
                User user = userService.findUserByMailAndPassword(mail, password);
                HttpSession session = request.getSession();
                session.setAttribute("user", user);

                session.setAttribute("isAdmin", false); // FIXME: 05.02.2020

                if (user.getRoleID() == 1) {
                    session.setAttribute("isAdmin", true);
                    page = PagePath.PAGE_ADMIN_PROFILE;
                } else {
                    session.setAttribute("isUser", true);
                    page = PagePath.PAGE_PROFILE;
                }
            } catch (ServiceException e) {
                logger.log(Level.INFO, "incorrect data", e);
                request.setAttribute(PageMessage.MESSAGE_ERROR, e.toString());

//                HttpSession session = request.getSession();
//                session.setAttribute(PageMessage.MESSAGE_ERROR, e.toString()); // FIXME: 27.01.2020 нормальную вальдацию и не в сессию ложить
                page = PagePath.PAGE_LOGIN;
            }
        }
        return page;
    }
}