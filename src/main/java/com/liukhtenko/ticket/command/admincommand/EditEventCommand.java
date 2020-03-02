package com.liukhtenko.ticket.command.admincommand;

import com.liukhtenko.ticket.command.Command;
import com.liukhtenko.ticket.command.CommandHelper;
import com.liukhtenko.ticket.command.FormParameterName;
import com.liukhtenko.ticket.command.PagePath;
import com.liukhtenko.ticket.entity.Event;
import com.liukhtenko.ticket.exception.ServiceException;
import com.liukhtenko.ticket.service.impl.EventService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * The class that edit event.
 *
 * @author Vladimir Liukhtenko
 * @version 1.25 02 Feb 2020
 */
public class EditEventCommand implements Command {
    private static Logger logger = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute(FormParameterName.TYPE_METHOD, FormParameterName.GET);
        EventService eventService = new EventService();
        try {
            List<Event> events = eventService.findAllEvents();
            CommandHelper.viewEvents(request, events);
        } catch (ServiceException e) {
            request.setAttribute(FormParameterName.FORM_PARAM_MESSAGE_ERROR, "Unable to edit event");
            logger.log(Level.ERROR, "Error in EditEventCommand", e);
            return PagePath.PAGE_ERROR;
        }
        return PagePath.PAGE_EDIT_EVENTS;
    }
}