package com.liukhtenko.ticket.command.impl;

import com.liukhtenko.ticket.command.Command;
import com.liukhtenko.ticket.command.CommandHelper;
import com.liukhtenko.ticket.command.PageMessage;
import com.liukhtenko.ticket.command.PagePath;
import com.liukhtenko.ticket.dao.impl.EventDao;
import com.liukhtenko.ticket.entity.Event;
import com.liukhtenko.ticket.entity.TypeEvent;
import com.liukhtenko.ticket.entity.User;
import com.liukhtenko.ticket.exception.ServiceException;
import com.liukhtenko.ticket.service.impl.EventService;
import com.liukhtenko.ticket.validator.FormValidator;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.Date;

public class CreateTicketCommand extends Command {
    @Override
    public String execute(HttpServletRequest request) {
        User user = CommandHelper.findUserInSession(request);
        if (user == null) {
            return PagePath.PAGE_LOGIN;
        } else if (user.getRoleID() != 1) {
            return PagePath.PAGE_ERROR;
        }
        String page = null;
        if (!FormValidator.isPost(request)
                || (FormValidator.isPost(request) && request.getParameter("addEvent") != null))
        {
            page = PagePath.PAGE_CREATE_EVENT;
        } else {
            EventService eventService = new EventService();
            Event event = new Event();
        }

        return page;
    }
}
