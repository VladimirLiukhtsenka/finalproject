package com.liukhtenko.ticket.command.impl;

import com.liukhtenko.ticket.command.*;
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
        if (user == null) {     // FIXME: 03.02.2020 maybe delete
            return PagePath.PAGE_LOGIN;
        } else if (user.getRoleID() != FormParameterName.ADMIN_ID) {
            return PagePath.PAGE_ERROR;
        }
        String page = null;
        if (request.getParameter("delete ticket") != null) {
            TicketService ticketService = new TicketService();
            long id = Long.parseLong(request.getParameter("id")); // FIXME: 03.02.2020
            try {
                ticketService.deleteTicketById(id);
            } catch (ServiceException e) {
                page = PagePath.PAGE_ERROR; // FIXME: 03.02.2020
                request.setAttribute(PageMessage.MESSAGE_ERROR, "mistake");
                return page;
            }
            page = PagePath.PAGE_EDIT_TICKET; // FIXME: 03.02.2020
            request.setAttribute(PageMessage.MESSAGE_ERROR, "тут метод!");
        }
        return page;
    }
}
