package com.liukhtenko.ticket.service;

import com.liukhtenko.ticket.entity.TicketOffice;
import com.liukhtenko.ticket.exception.ServiceException;

import java.util.List;

public interface TicketOfficeInterface {
    List<TicketOffice> findAllTicketOffice() throws ServiceException;

    void deleteTicketOfficeByPhone(String phone) throws ServiceException;

    boolean createTicketOffice(TicketOffice ticketOffice) throws ServiceException;
}
