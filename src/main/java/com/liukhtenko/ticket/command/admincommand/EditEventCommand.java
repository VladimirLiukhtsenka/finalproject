package com.liukhtenko.ticket.command.admincommand;

import com.liukhtenko.ticket.command.*;
import com.liukhtenko.ticket.entity.Event;
import com.liukhtenko.ticket.exception.ServiceException;
import com.liukhtenko.ticket.service.impl.EventService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


public class EditEventCommand implements Command {
    static Logger logger = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        request.setAttribute(FormParameterName.TYPE_METHOD,FormParameterName.GET);
        EventService eventService = new EventService();
        request.setAttribute(PageMessage.MESSAGE,"админ"); // FIXME: 23.02.2020
        try {
            List<Event> events = eventService.findAllEvents();
            CommandHelper.viewEvents(request, events);
        } catch (ServiceException e) {
            request.setAttribute(PageMessage.MESSAGE_ERROR, "Unable to edit event");
            logger.log(Level.ERROR, "Error in EditEventCommand", e);
            page = PagePath.PAGE_ERROR;
            return page;
        }
        return PagePath.PAGE_EDIT_EVENTS;
    }
}