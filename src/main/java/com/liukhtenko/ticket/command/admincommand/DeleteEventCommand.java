package com.liukhtenko.ticket.command.admincommand;

import com.liukhtenko.ticket.command.*;
import com.liukhtenko.ticket.dao.ColumnName;
import com.liukhtenko.ticket.entity.Event;
import com.liukhtenko.ticket.exception.ServiceException;
import com.liukhtenko.ticket.service.impl.EventService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class DeleteEventCommand implements Command {
    static Logger logger = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        request.setAttribute(FormParameterName.TYPE_METHOD,FormParameterName.GET);
        if (request.getParameter(FormParameterName.FORM_PARAM_DELETE_EVENT) != null) {
            EventService eventService = new EventService();
            long id = Long.parseLong(request.getParameter(ColumnName.ID)); // FIXME: 23.02.2020 
            try {
                eventService.deleteEventById(id);
                request.setAttribute(FormParameterName.TYPE_METHOD,FormParameterName.POST);
//                List<Event> events = eventService.findAllEvents();
//                CommandHelper.viewEvents(request, events);
            } catch (ServiceException e) {
                page = PagePath.PAGE_ERROR;
                request.setAttribute(PageMessage.MESSAGE_ERROR, "Unable to delete event");
                logger.log(Level.ERROR, "Error in DeleteEventCommand", e);
                return page;
            }
            page = PagePath.PAGE_EDIT_EVENTS;
        }
        return page;
    }
}