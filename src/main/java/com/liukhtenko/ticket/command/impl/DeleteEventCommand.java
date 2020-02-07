package com.liukhtenko.ticket.command.impl;

import com.liukhtenko.ticket.command.*;
import com.liukhtenko.ticket.entity.User;
import com.liukhtenko.ticket.exception.ServiceException;
import com.liukhtenko.ticket.service.impl.EventService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class DeleteEventCommand extends Command {
    static Logger logger = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request) {
        User user = CommandHelper.findUserInSession(request);
        if (user == null) {     // FIXME: 03.02.2020 maybe delete
            return PagePath.PAGE_LOGIN;
        } else if (user.getRoleID() != FormParameterName.ADMIN_ID) {
            return PagePath.PAGE_ERROR;
        }
        String page = null;
        if (request.getParameter("delete event") != null) {
            EventService eventService = new EventService();
            long id = Long.parseLong(request.getParameter("id")); // FIXME: 03.02.2020
            try {
                eventService.deleteEventById(id);
            } catch (ServiceException e) {
                page = PagePath.PAGE_ERROR; // FIXME: 03.02.2020
                request.setAttribute(PageMessage.MESSAGE_ERROR, "mistake");
                return page;
            }
            page = PagePath.PAGE_EDIT_EVENTS; // FIXME: 03.02.2020
        }
        return page;
    }
}