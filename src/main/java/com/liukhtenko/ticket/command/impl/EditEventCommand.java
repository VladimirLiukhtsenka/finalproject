package com.liukhtenko.ticket.command.impl;

import com.liukhtenko.ticket.command.*;
import com.liukhtenko.ticket.entity.Event;
import com.liukhtenko.ticket.exception.ServiceException;
import com.liukhtenko.ticket.service.impl.EventService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


public class EditEventCommand extends Command {
    static Logger logger = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        EventService eventService = new EventService();
        try {
            List<Event> events = eventService.findAllEvents();
            CommandHelper.ViewEvents(request, events);
        } catch (ServiceException e) {
            request.setAttribute(PageMessage.MESSAGE_ERROR, "Unable to edit event");
            logger.log(Level.ERROR, "Error in EditEventCommand", e);
            page = PagePath.PAGE_ERROR;
            return page;
        }
        return PagePath.PAGE_EDIT_EVENTS;
    }
}