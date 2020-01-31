package com.liukhtenko.ticket.command.impl;

import com.liukhtenko.ticket.command.Command;
import com.liukhtenko.ticket.command.PageMessage;
import com.liukhtenko.ticket.command.PagePath;
import com.liukhtenko.ticket.entity.User;
import com.liukhtenko.ticket.exception.ServiceException;
import com.liukhtenko.ticket.service.UserService;
import com.liukhtenko.ticket.validator.FormRegexValidator;
import com.liukhtenko.ticket.validator.FormValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LoginCommand extends Command {
    static Logger logger = LogManager.getLogger();

    private static final String FORM_PARAM_PASSWORD = "password";  // FIXME: 29.01.2020 одинаклвые поля объединить
    private static final String FORM_PARAM_MAIL = "mail";
    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        if (!FormValidator.isPost(request)) {
            page = PagePath.PAGE_LOGIN;
            return page;
        } else {
            try {

                String mail = "";
                if (FormValidator.isValidString(request.getParameter(FORM_PARAM_MAIL), FormRegexValidator.EMAIL)) {
                    mail = request.getParameter(FORM_PARAM_MAIL);
                }
                String password ="";
                if (FormValidator.isValidString(request.getParameter(FORM_PARAM_PASSWORD), FormRegexValidator.PASSWORD)) {
                     password = request.getParameter(FORM_PARAM_PASSWORD);
                }
                UserService userService = new UserService();
                User user = userService.findUserByMailAndPassword(mail, password);
                if (user.getMail() != null) { // FIXME: 31.01.2020 delete if
                    HttpSession session = request.getSession();
                    session.setAttribute("user", user.getName());
                    page = PagePath.PAGE_PROFILE;
                    return page;
                } else {
                    request.setAttribute(PageMessage.MESSAGE, " Wrong data");
                    return page;
                }
            } catch ( ServiceException e) {
                logger.log(Level.INFO, "incorrect data", e);
                request.setAttribute(PageMessage.MESSAGE_ERROR, e.toString()); // FIXME: 27.01.2020
                page = null;
            }
        }
        return page;
    }
}