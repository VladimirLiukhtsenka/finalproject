package com.liukhtenko.ticket.command.impl;

import com.liukhtenko.ticket.command.Command;
import com.liukhtenko.ticket.command.PageMessage;
import com.liukhtenko.ticket.command.PagePath;
import com.liukhtenko.ticket.entity.Event;
import com.liukhtenko.ticket.exception.ServiceException;
import com.liukhtenko.ticket.service.impl.EventService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class EditTicketCommand extends Command {
    @Override
    public String execute(HttpServletRequest request) {
        String page;
        EventService eventService = new EventService();
        try {
            List<Event> events = eventService.findAllEvents();
            request.setAttribute("events",events);
        } catch (ServiceException e) {
            HttpSession session = request.getSession();
            session.setAttribute(PageMessage.MESSAGE_ERROR, e.toString()); // FIXME: 27.01.2020 нормальную вальдац
            page = PagePath.PAGE_ERROR;
            return page;
        }
        return PagePath.PAGE_EDIT_TICKET; // FIXME: 02.02.2020
    }
}