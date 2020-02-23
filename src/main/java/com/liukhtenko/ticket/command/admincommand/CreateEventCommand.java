package com.liukhtenko.ticket.command.admincommand;

import com.liukhtenko.ticket.command.*;
import com.liukhtenko.ticket.dao.ColumnName;
import com.liukhtenko.ticket.dao.impl.EventDao;
import com.liukhtenko.ticket.entity.Event;
import com.liukhtenko.ticket.entity.TypeEvent;
import com.liukhtenko.ticket.exception.ServiceException;
import com.liukhtenko.ticket.service.impl.EventService;
import com.liukhtenko.ticket.validator.FormRegexValidator;
import com.liukhtenko.ticket.validator.FormValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

public class CreateEventCommand implements Command {
    static Logger logger = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        request.setAttribute(FormParameterName.TYPE_METHOD,FormParameterName.GET);
        if (!FormValidator.isPost(request)
                || (FormValidator.isPost(request) && request.getParameter(FormParameterName.FORM_PARAM_ADD_EVENT) != null)) {
            request.setAttribute(PageMessage.MESSAGE,"админ"); // FIXME: 23.02.2020
            page = PagePath.PAGE_CREATE_EVENT;

        } else {
            EventService eventService = new EventService();
            Event event = new Event();
            try {
                String name = request.getParameter(ColumnName.NAME);
               // if (FormValidator.isValidString(name, FormRegexValidator.EVENT_NAME)) {
                    event.setName(name);
               // }
                String address = request.getParameter(ColumnName.ADDRESS);
                if (FormValidator.isValidString(address, FormRegexValidator.EVENT_ADDRESS)) {
                    event.setAddress(address);
                }
                String description = request.getParameter(ColumnName.DESCRIPTION);
                if (FormValidator.isValidString(description, FormRegexValidator.EVENT_DESCRIPTION)) {
                    event.setDescription(description);
                }
                TypeEvent typeEvent = TypeEvent.findByType(request.getParameter(ColumnName.TYPE_EVENT));
                event.setTypeOfEvent(typeEvent);
                String date = request.getParameter(ColumnName.DATE);
                if (FormValidator.isValidDate(date)) {
                    Date moment = EventDao.transformDate(date);
                    event.setDate(moment);
                }
                if (eventService.createEvent(event)) {
                    List<Event> events = eventService.findAllEvents();
                    CommandHelper.viewEvents(request, events);
                    page = PagePath.PAGE_EDIT_EVENTS;
                    request.setAttribute(FormParameterName.TYPE_METHOD,FormParameterName.POST);
                    return page;
                }
//                HttpSession session = request.getSession();
//                session.setAttribute(FormParameterName.REDIRECT_SECURE, "true"); // FIXME: 23.02.2020
            } catch (ServiceException | ParseException e) {
                logger.log(Level.ERROR, "Error in CreateEventCommand", e);
                request.setAttribute(PageMessage.MESSAGE_ERROR, "Impossible create event.");
                return PagePath.PAGE_CREATE_EVENT;
            }
        }

        return page;
    }
}