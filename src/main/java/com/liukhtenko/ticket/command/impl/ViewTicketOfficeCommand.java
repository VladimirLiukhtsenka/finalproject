package com.liukhtenko.ticket.command.impl;

import com.liukhtenko.ticket.command.Command;
import com.liukhtenko.ticket.command.PageMessage;
import com.liukhtenko.ticket.command.PagePath;
import com.liukhtenko.ticket.entity.TicketOffice;
import com.liukhtenko.ticket.exception.ServiceException;
import com.liukhtenko.ticket.service.impl.TicketOfficeService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ViewTicketOfficeCommand extends Command {
    @Override
    public String execute(HttpServletRequest request) {
        String page;
        TicketOfficeService ticketOfficeService = new TicketOfficeService();
        try {
            List<TicketOffice> ticketOffices = ticketOfficeService.findAllTicketOffice();
            request.setAttribute("ticketOffices",ticketOffices);
        } catch (ServiceException e) {
            request.setAttribute(PageMessage.MESSAGE_ERROR,e.toString()); // FIXME: 02.02.2020
            page = PagePath.PAGE_ERROR;
            return page;
        }
       
        return PagePath.PAGE_VIEW_TICKET_OFFICE; // FIXME: 02.02.2020
    }
}