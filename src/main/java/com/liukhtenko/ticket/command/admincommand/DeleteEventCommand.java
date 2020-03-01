package com.liukhtenko.ticket.command.admincommand;

import com.liukhtenko.ticket.command.Command;
import com.liukhtenko.ticket.command.FormParameterName;
import com.liukhtenko.ticket.command.PagePath;
import com.liukhtenko.ticket.dao.ColumnName;
import com.liukhtenko.ticket.exception.ServiceException;
import com.liukhtenko.ticket.service.impl.EventService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * The class that delete event.
 *
 * @author Vladimir Liukhtenko
 * @version 1.25 02 Feb 2020
 */
public class DeleteEventCommand implements Command {
    private static Logger logger = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        request.setAttribute(FormParameterName.TYPE_METHOD, FormParameterName.GET);
        if (request.getParameter(FormParameterName.FORM_PARAM_DELETE_EVENT) != null) {
            EventService eventService = new EventService();
            long id = 0;
            if (request.getParameter(ColumnName.ID) != null) {
                id = Long.parseLong(request.getParameter(ColumnName.ID));
            }
            try {
                eventService.deleteEventById(id);
                request.setAttribute(FormParameterName.TYPE_METHOD, FormParameterName.POST);
            } catch (ServiceException e) {
                page = PagePath.PAGE_ERROR;
                request.setAttribute(FormParameterName.FORM_PARAM_MESSAGE_ERROR, "Unable to delete event");
                logger.log(Level.ERROR, "Error in DeleteEventCommand", e);
                return page;
            }
            page = PagePath.PAGE_EDIT_EVENTS;
        }
        return page;
    }
}