package com.liukhtenko.ticket.service;

import com.liukhtenko.ticket.entity.Ticket;
import com.liukhtenko.ticket.exception.ServiceException;

import java.util.List;

/**
 * Interface for
 * {@link com.liukhtenko.ticket.service.impl.TicketOfficeService}
 *
 * @author Vladimir Liukhtenko
 * @version 1.25 02 Feb 2020
 */
public interface TicketServiceInterface {
    int buyTicket(long userId, long ticketId) throws ServiceException;

    List<Ticket> findTicketsByEventId(long id) throws ServiceException;

    List<Ticket> findTicketsByEventIdAndTypeSeat(long id, String type) throws ServiceException;

    List<List<String>> printTickets(long UserId) throws ServiceException;

    boolean createTicket(Ticket ticket) throws ServiceException;

    boolean deleteTicketById(long id) throws ServiceException;

}
