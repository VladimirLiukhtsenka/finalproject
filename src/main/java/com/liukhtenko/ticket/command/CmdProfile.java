package com.liukhtenko.ticket.command;

import com.liukhtenko.ticket.validator.FormValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class CmdProfile extends Action {
    @Override
    public String execute(HttpServletRequest request) {
        if (FormValidator.isPost(request) && request.getParameter("logout") != null) {
            HttpSession session = request.getSession();
            session.invalidate();
           // String result = new CmdLogin().execute(request);
            String result = "Login";
            return result;
        }
        return null;
    }
}
