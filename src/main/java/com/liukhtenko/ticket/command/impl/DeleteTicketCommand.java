package com.liukhtenko.ticket.command.impl;

import com.liukhtenko.ticket.command.*;
import com.liukhtenko.ticket.dao.ColumnName;
import com.liukhtenko.ticket.entity.User;
import com.liukhtenko.ticket.exception.ServiceException;
import com.liukhtenko.ticket.service.impl.TicketService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class DeleteTicketCommand extends Command {
    static Logger logger = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        if (request.getParameter(FormParameterName.FORM_PARAM_DELETE_TICKET) != null) {
            TicketService ticketService = new TicketService();
            long id = Long.parseLong(request.getParameter(ColumnName.ID));
            try {
                ticketService.deleteTicketById(id);
            } catch (ServiceException e) {
                page = PagePath.PAGE_ERROR;
                request.setAttribute(PageMessage.MESSAGE_ERROR, "Unable to delete ticket");
                logger.log(Level.ERROR, "Error in DeleteTicketCommand", e);
                return page;
            }
            page = PagePath.PAGE_EDIT_TICKET;
        }
        return page;
    }
}
