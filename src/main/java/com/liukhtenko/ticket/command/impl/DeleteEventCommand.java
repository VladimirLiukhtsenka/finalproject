package com.liukhtenko.ticket.command.impl;

import com.liukhtenko.ticket.command.Command;
import com.liukhtenko.ticket.command.FormParameterName;
import com.liukhtenko.ticket.command.PageMessage;
import com.liukhtenko.ticket.command.PagePath;
import com.liukhtenko.ticket.dao.ColumnName;
import com.liukhtenko.ticket.exception.ServiceException;
import com.liukhtenko.ticket.service.impl.EventService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class DeleteEventCommand extends Command {
    static Logger logger = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        if (request.getParameter(FormParameterName.FORM_PARAM_DELETE_EVENT) != null) {
            EventService eventService = new EventService();
            long id = Long.parseLong(request.getParameter(ColumnName.ID));
            try {
                eventService.deleteEventById(id);
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