package com.liukhtenko.ticket.command.viewcommand;

import com.liukhtenko.ticket.command.Command;
import com.liukhtenko.ticket.command.CommandHelper;
import com.liukhtenko.ticket.command.FormParameterName;
import com.liukhtenko.ticket.command.PagePath;
import com.liukhtenko.ticket.entity.Event;
import com.liukhtenko.ticket.entity.TypeEvent;
import com.liukhtenko.ticket.exception.ServiceException;
import com.liukhtenko.ticket.service.impl.EventService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * The class allows to view theater events..
 *
 * @author Vladimir Liukhtenko
 * @version 1.25 02 Feb 2020
 */
public class ViewTheaterEventCommand implements Command {
    private static Logger logger = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request) {
        String page = PagePath.PAGE_THEATER_EVENTS;
        request.setAttribute(FormParameterName.TYPE_METHOD, FormParameterName.GET);
        EventService eventService = new EventService();
        try {
            List<Event> events = eventService.findEventByType(TypeEvent.THEATER.getValue());
            CommandHelper.viewEvents(request, events);
        } catch (ServiceException e) {
            logger.log(Level.WARN, "Error in ViewTheaterEventCommand", e);
            request.setAttribute(FormParameterName.FORM_PARAM_MESSAGE_ERROR, "Impossible to view theater events.");
            page = PagePath.PAGE_ERROR;
            return page;
        }
        return page;
    }
}
