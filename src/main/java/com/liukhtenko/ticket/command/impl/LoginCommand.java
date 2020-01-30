package com.liukhtenko.ticket.command.impl;

import com.liukhtenko.ticket.command.Command;
import com.liukhtenko.ticket.command.PageMessage;
import com.liukhtenko.ticket.command.PagePath;
import com.liukhtenko.ticket.entity.User;
import com.liukhtenko.ticket.exception.ServiceException;
import com.liukhtenko.ticket.exception.SiteException;
import com.liukhtenko.ticket.service.UserService;
import com.liukhtenko.ticket.validator.FormRegexValidator;
import com.liukhtenko.ticket.validator.FormValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LoginCommand extends Command {
    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        if (!FormValidator.isPost(request)) {
            page = PagePath.PAGE_LOGIN;
            return page;
        } else {
            try {
                String mail = FormValidator.getString(request, "textinputMail", FormRegexValidator.EMAIL); // FIXME: 29.01.2020 in CONST
                String password = FormValidator.getString(request, "passwordinput", FormRegexValidator.PASSWORD);
                UserService userService = new UserService();
                User user = userService.findUserByMailAndPassword(mail, password);
                if (user.getMail() != null) {
                    HttpSession session = request.getSession();
                    session.setAttribute("user", user.getName());
                    page = PagePath.PAGE_PROFILE;
                    return page;
                } else {
                    request.setAttribute(PageMessage.MESSAGE, " Wrong data");
                    return page;
                }
            } catch (SiteException | ServiceException e) {
                e.printStackTrace(); // FIXME: 27.01.2020
                request.setAttribute(PageMessage.MESSAGE_ERROR, e.toString()); // FIXME: 27.01.2020
                page = null;
            }
        }
        return page;
    }
}