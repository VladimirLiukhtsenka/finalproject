package com.liukhtenko.ticket.command.admincommand;

import com.liukhtenko.ticket.command.Command;
import com.liukhtenko.ticket.command.FormParameterName;
import com.liukhtenko.ticket.command.PagePath;
import com.liukhtenko.ticket.validator.FormValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class AdminProfileCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        request.setAttribute(FormParameterName.TYPE_METHOD, FormParameterName.GET);
        if (!FormValidator.isPost(request)) {
            page = PagePath.PAGE_ADMIN_PROFILE;
        }
        if (FormValidator.isPost(request) && request.getParameter(FormParameterName.FORM_PARAM_LOGOUT) != null) {
            HttpSession session = request.getSession();
            session.invalidate();
            page = PagePath.PAGE_LOGIN;
            return page;
        }
        return page;
    }
}
