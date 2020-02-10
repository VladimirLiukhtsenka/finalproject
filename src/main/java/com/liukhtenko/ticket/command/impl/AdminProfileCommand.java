package com.liukhtenko.ticket.command.impl;

import com.liukhtenko.ticket.command.Command;
import com.liukhtenko.ticket.command.CommandHelper;
import com.liukhtenko.ticket.command.FormParameterName;
import com.liukhtenko.ticket.command.PagePath;
import com.liukhtenko.ticket.entity.User;
import com.liukhtenko.ticket.validator.FormValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class AdminProfileCommand extends Command {
    @Override
    public String execute(HttpServletRequest request) {
        String page;
        if (!FormValidator.isPost(request)) {
            page = PagePath.PAGE_ADMIN_PROFILE;
            return page;
        } else page = PagePath.PAGE_LOGIN;
        if (FormValidator.isPost(request) && request.getParameter(FormParameterName.FORM_PARAM_LOGOUT) != null) {
            HttpSession session = request.getSession();
            session.invalidate();
            page = PagePath.PAGE_LOGIN;
            return page;
        }
        return page;
    }
}
