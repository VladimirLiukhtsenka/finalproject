package com.liukhtenko.ticket;

import com.liukhtenko.ticket.entity.Ticket;
import com.liukhtenko.ticket.exception.ServiceException;
import com.liukhtenko.ticket.service.TicketService;

import java.util.List;

public class TestTicketService {
    public static void main(String[] args) throws ServiceException {
        TicketService ticketService = new TicketService();
        List<Ticket> list = ticketService.findTicketsByEventId(1);
        System.out.println(list);
        List<Ticket> list1 = ticketService.findTicketsByEventIdAndTypeSeat(1,"Сектор А");
        System.out.println(list1);
    }
}
