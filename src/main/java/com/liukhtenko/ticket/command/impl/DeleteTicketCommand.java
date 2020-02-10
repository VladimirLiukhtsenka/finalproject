package com.liukhtenko.ticket.command.impl;

import com.liukhtenko.ticket.command.*;
import com.liukhtenko.ticket.dao.ColumnName;
import com.liukhtenko.ticket.entity.User;
import com.liukhtenko.ticket.exception.ServiceException;
import com.liukhtenko.ticket.service.impl.TicketService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class DeleteTicketCommand extends Command {
    static Logger logger = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request) {
        User user = CommandHelper.findUserInSession(request);
        String page = null;
        if (request.getParameter(FormParameterName.FORM_PARAM_DELETE_TICKET) != null) {
            TicketService ticketService = new TicketService();
            long id = Long.parseLong(request.getParameter(ColumnName.ID)); // FIXME: 03.02.2020
            try {
                ticketService.deleteTicketById(id);
            } catch (ServiceException e) {
                page = PagePath.PAGE_ERROR; // FIXME: 03.02.2020
                request.setAttribute(PageMessage.MESSAGE_ERROR, "Unable to delete ticket");
                return page;
            }
            page = PagePath.PAGE_EDIT_TICKET; // FIXME: 03.02.2020
            request.setAttribute(PageMessage.MESSAGE_ERROR, "Event deleted");
        }
        return page;
    }
}
