package com.liukhtenko.ticket.command.impl;

import com.liukhtenko.ticket.command.*;
import com.liukhtenko.ticket.dao.ColumnName;
import com.liukhtenko.ticket.dao.impl.EventDao;
import com.liukhtenko.ticket.entity.Event;
import com.liukhtenko.ticket.entity.TypeEvent;
import com.liukhtenko.ticket.entity.User;
import com.liukhtenko.ticket.exception.ServiceException;
import com.liukhtenko.ticket.service.impl.EventService;
import com.liukhtenko.ticket.validator.FormValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.Date;

public class CreateEventCommand extends Command {
    static Logger logger = LogManager.getLogger();
    @Override
    public String execute(HttpServletRequest request) {
        User user = CommandHelper.findUserInSession(request);
        String page = null;
        if (!FormValidator.isPost(request)
         || (FormValidator.isPost(request) && request.getParameter(FormParameterName.FORM_PARAM_ADD_EVENT) != null))
        {
            page = PagePath.PAGE_CREATE_EVENT;
        } else {
            EventService eventService = new EventService();
            Event event = new Event();
            try {
             //   event.setId(1); // FIXME: 08.02.2020 возможно не нужен
                String name = request.getParameter(ColumnName.NAME); // FIXME: 02.02.2020  valid
                event.setName(name);
                String address = request.getParameter(ColumnName.ADDRESS);
                event.setAddress(address);
                String description = request.getParameter(ColumnName.DESCRIPTION);
                event.setDescription(description);
                TypeEvent typeEvent = TypeEvent.findByType(request.getParameter(ColumnName.TYPE_EVENT));
                event.setTypeOfEvent(typeEvent);
                String date = request.getParameter(ColumnName.DATE);
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