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

public class CreateEventCommand extends Command {
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
            try {
                event.setId(1);
                String name = request.getParameter("name"); // FIXME: 02.02.2020 in const and valid
                event.setName(name);
                String address = request.getParameter("address");
                event.setAddress(address);
                String description = request.getParameter("description");
                event.setDescription(description);
                TypeEvent typeEvent = TypeEvent.findByType(request.getParameter("type of event"));
                event.setTypeOfEvent(typeEvent);
                String date = request.getParameter("date");
                if (FormValidator.isValidDate(date)) {
                    Date moment = EventDao.transformDate(date);  // FIXME: 02.02.2020 method replace
                    event.setDate(moment);
                }
                if (eventService.createEvent(event)) {
                    page = PagePath.PAGE_EDIT_EVENTS; // FIXME: 02.02.2020 page administrator
                    return page;
                }
            } catch (ServiceException | ParseException e) {
                request.setAttribute(PageMessage.MESSAGE_ERROR, e.toString()); // FIXME: 02.02.2020
                return PagePath.PAGE_CREATE_EVENT; // FIXME: 02.02.2020
            }
        }

        return page;
    }
}