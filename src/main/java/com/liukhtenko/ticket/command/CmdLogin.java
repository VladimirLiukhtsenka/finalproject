package com.liukhtenko.ticket.command;

import com.liukhtenko.ticket.exception.SiteException;
import com.liukhtenko.ticket.service.UserService;
import com.liukhtenko.ticket.validator.CustomPattern;
import com.liukhtenko.ticket.validator.Form;

import javax.servlet.http.HttpServletRequest;

public class CmdLogin  extends Action {
    @Override
    public Action execute(HttpServletRequest request) {
        try {
            String test = Form.getString(request,"textinputMail", CustomPattern.Email);
            request.setAttribute(Messages.MESSAGE,"Hi: "+test);
        } catch (SiteException |NullPointerException e) {
            e.printStackTrace();
            request.setAttribute(Messages.MESSAGE_ERROR,"error ");
        }
        return null;
    }
}