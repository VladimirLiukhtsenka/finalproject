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

public class DeleteEventCommand extends Command {
    @Override
    public String execute(HttpServletRequest request) {
        User user = CommandHelper.findUserInSession(request);
        if (user == null) {     // FIXME: 03.02.2020 maybe delete
            return PagePath.PAGE_LOGIN;
        } else if (user.getRoleID() != 1) {
            return PagePath.PAGE_ERROR;
        }
        String page = null;
        if (request.getParameter("delete event")!=null) {
            EventService eventService = new EventService();
            long id = Long.parseLong( request.getParameter("id")); // FIXME: 03.02.2020
            try {
                eventService.deleteEventById(id);
            } catch (ServiceException e) {
                page = PagePath.PAGE_ERROR; // FIXME: 03.02.2020
                request.setAttribute(PageMessage.MESSAGE_ERROR,"mistake");
                return page;
            }
            page = PagePath.PAGE_EDIT_EVENTS; // FIXME: 03.02.2020
        }
        return page;
    }
}