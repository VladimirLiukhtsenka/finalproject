package com.liukhtenko.ticket.command;

import com.liukhtenko.ticket.PagePath;
import com.liukhtenko.ticket.validator.FormValidator;

import javax.servlet.http.HttpServletRequest;

public class LogOutCommand extends Action {
    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        if (!FormValidator.isPost(request)) {
//            return null;
            page = PagePath.PAGE_LOGOUT;
        }
        return page;
    }
}