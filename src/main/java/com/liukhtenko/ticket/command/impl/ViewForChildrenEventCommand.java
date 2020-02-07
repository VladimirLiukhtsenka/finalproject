package com.liukhtenko.ticket.command.impl;

import com.liukhtenko.ticket.command.Command;
import com.liukhtenko.ticket.command.PageMessage;
import com.liukhtenko.ticket.command.PagePath;
import com.liukhtenko.ticket.entity.Event;
import com.liukhtenko.ticket.entity.TypeEvent;
import com.liukhtenko.ticket.exception.ServiceException;
import com.liukhtenko.ticket.service.impl.EventService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ViewForChildrenEventCommand extends Command {
    static Logger logger = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        EventService eventService = new EventService();
        try {
            List<Event> events = eventService.findEventByType(TypeEvent.FOR_CHILDREN.getValue());
            request.setAttribute("events", events);
        } catch (ServiceException e) {
            request.setAttribute(PageMessage.MESSAGE_ERROR, e.toString()); // FIXME: 02.02.2020
            page = PagePath.PAGE_ERROR;
            return page;
        }

        return PagePath.PAGE_FOR_CHILDREN_EVENTS; // FIXME: 02.02.2020
    }
}
