package com.liukhtenko.ticket.service;

import com.liukhtenko.ticket.entity.TicketOffice;
import com.liukhtenko.ticket.exception.ServiceException;

import java.util.List;

/**
 * Interface for
 * {@link com.liukhtenko.ticket.service.impl.TicketOfficeService}
 *
 * @author Vladimir Liukhtenko
 * @version 1.25 02 Feb 2020
 */
public interface TicketOfficeInterface {
    List<TicketOffice> findAllTicketOffice() throws ServiceException;

    void deleteTicketOfficeByPhone(String phone) throws ServiceException;

    boolean createTicketOffice(TicketOffice ticketOffice) throws ServiceException;
}
