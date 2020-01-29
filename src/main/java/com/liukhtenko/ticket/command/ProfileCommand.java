package com.liukhtenko.ticket.command;

import com.liukhtenko.ticket.PagePath;
import com.liukhtenko.ticket.validator.FormValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ProfileCommand extends Action {
    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        if (!FormValidator.isPost(request)) {
//            return null;
            page = PagePath.PAGE_PROFILE;
            return page;
        }
        if (FormValidator.isPost(request) && request.getParameter("logout") != null) {
            HttpSession session = request.getSession();
            session.invalidate();
           // String result = new LoginCommand().execute(request);

//            String result = "Login";
            page = PagePath.PAGE_LOGIN;
            return page;
        }
        return page;
    }
}
