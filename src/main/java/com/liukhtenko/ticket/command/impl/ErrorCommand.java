package com.liukhtenko.ticket.command.impl;

import com.liukhtenko.ticket.command.Command;
import com.liukhtenko.ticket.command.PagePath;
import com.liukhtenko.ticket.validator.FormValidator;

import javax.servlet.http.HttpServletRequest;

public class ErrorCommand extends Command {
    @Override
    public String execute(HttpServletRequest request) {
        String page = PagePath.PAGE_ERROR;
//        if (!FormValidator.isPost(request)) {// FIXME: 04.02.2020 
//            page = PagePath.PAGE_ERROR;
//        }
        return page;
    }
}
