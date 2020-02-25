package com.liukhtenko.ticket.service;

import com.liukhtenko.ticket.entity.Ticket;
import com.liukhtenko.ticket.exception.ServiceException;

import java.util.List;

public interface TicketServiceInterface {
    int buyTicket(long userId, long ticketId) throws ServiceException;

    List<Ticket> findTicketsByEventId(long id) throws ServiceException;

    List<Ticket> findTicketsByEventIdAndTypeSeat(long id, String type) throws ServiceException;

    List<List<String>> printTickets(long UserId) throws ServiceException;

    boolean createTicket(Ticket ticket) throws ServiceException;

    boolean deleteTicketById(long id) throws ServiceException;

}
