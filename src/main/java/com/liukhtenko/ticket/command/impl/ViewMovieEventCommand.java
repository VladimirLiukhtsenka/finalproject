package com.liukhtenko.ticket.command.impl;

import com.liukhtenko.ticket.command.*;
import com.liukhtenko.ticket.entity.Event;
import com.liukhtenko.ticket.entity.TypeEvent;
import com.liukhtenko.ticket.exception.ServiceException;
import com.liukhtenko.ticket.service.impl.EventService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ViewMovieEventCommand extends Command {
    static Logger logger = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        EventService eventService = new EventService();
        try {
            List<Event> events = eventService.findEventByType(TypeEvent.MOVIE.getValue());
            CommandHelper.ViewEvents(request, events);
        } catch (ServiceException e) {
            logger.log(Level.WARN, "Error in ViewMovieEventCommand", e);
            request.setAttribute(PageMessage.MESSAGE_ERROR, "Impossible to view movie events.");
            page = PagePath.PAGE_ERROR;
            return page;
        }

        return PagePath.PAGE_MOVIE_EVENTS;
    }
}