package com.liukhtenko.ticket.service.impl;

import com.liukhtenko.ticket.dao.EntityTransaction;
import com.liukhtenko.ticket.dao.impl.TicketDao;
import com.liukhtenko.ticket.entity.Ticket;
import com.liukhtenko.ticket.exception.DaoException;
import com.liukhtenko.ticket.exception.ServiceException;
import com.liukhtenko.ticket.service.TicketServiceInterface;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class TicketService implements TicketServiceInterface {
    static Logger logger = LogManager.getLogger();

    public int buyTicket(long userId, long ticketId) throws ServiceException {
        int numberTicket;
        TicketDao ticketDao = new TicketDao();
        EntityTransaction transaction = new EntityTransaction();
        try {
            transaction.begin(ticketDao);
            numberTicket = ticketDao.buyTicket(userId, ticketId);
            transaction.commit();
            logger.log(Level.DEBUG, "buyTicket completed successfully");
        } catch (DaoException e) {
            transaction.rollback();
            throw new ServiceException(e);
        } finally {
            transaction.end();
        }
        return numberTicket;
    }


    public List<Ticket> findTicketsByEventId(long id) throws ServiceException {
        List<Ticket> tickets;
        TicketDao ticketDao = new TicketDao();
        EntityTransaction transaction = new EntityTransaction();
        try {
            transaction.begin(ticketDao);
            tickets = ticketDao.findTicketsByEventId(id);
            transaction.commit();
            logger.log(Level.DEBUG, "findTicketsByEventId completed successfully");
        } catch (DaoException e) {
            transaction.rollback();
            throw new ServiceException(e);
        } finally {
            transaction.end();
        }
        return tickets;
    }

    public List<Ticket> findTicketsByEventIdAndTypeSeat(long id, String type) throws ServiceException {
        List<Ticket> tickets;
        TicketDao ticketDao = new TicketDao();
        EntityTransaction transaction = new EntityTransaction();
        try {
            transaction.begin(ticketDao);
            tickets = ticketDao.findTicketsByEventIdAndTypeSeat(id, type);
            transaction.commit();
            logger.log(Level.DEBUG, "findTicketsByEventIdAndTypeSeat completed successfully");
        } catch (DaoException e) {
            transaction.rollback();
            throw new ServiceException(e);
        } finally {
            transaction.end();
        }
        return tickets;
    }
}