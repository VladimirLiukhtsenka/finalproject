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

/**
 * Class that management tickets.
 *
 * @author Vladimir Liukhtenko
 * @version 1.25 02 Feb 2020
 */
public class TicketService implements TicketServiceInterface {
    private static Logger logger = LogManager.getLogger();

    public int buyTicket(long userId, long ticketId) throws ServiceException {
        int numberTicket;
        TicketDao ticketDao = new TicketDao();
        EntityTransaction transaction = new EntityTransaction();
        try {
            transaction.begin(ticketDao);
            numberTicket = ticketDao.buyTicket(userId, ticketId);
            logger.log(Level.DEBUG, "buyTicket completed successfully");
        } catch (DaoException e) {
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
            logger.log(Level.DEBUG, "findTicketsByEventId completed successfully");
        } catch (DaoException e) {
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
            logger.log(Level.DEBUG, "findTicketsByEventIdAndTypeSeat completed successfully");
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transaction.end();
        }
        return tickets;
    }

    public List<List<String>> printTickets(long UserId) throws ServiceException {
        List<List<String>> printUserTickets;
        TicketDao ticketDao = new TicketDao();
        EntityTransaction transaction = new EntityTransaction();
        try {
            transaction.begin(ticketDao);
            printUserTickets = ticketDao.printTickets(UserId);
            logger.log(Level.DEBUG, "printTickets completed successfully");
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transaction.end();
        }
        return printUserTickets;
    }

    public boolean createTicket(Ticket ticket) throws ServiceException {
        TicketDao ticketDao = new TicketDao();
        EntityTransaction transaction = new EntityTransaction();
        boolean flag;
        try {
            transaction.begin(ticketDao);
            flag = ticketDao.create(ticket);
            logger.log(Level.DEBUG, "createTicket completed successfully");
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transaction.end();
        }
        return flag;
    }

    public boolean deleteTicketById(long id) throws ServiceException {
        boolean flag;
        TicketDao ticketDao = new TicketDao();
        EntityTransaction transaction = new EntityTransaction();
        try {
            transaction.begin(ticketDao);
            flag = ticketDao.delete(id);
            logger.log(Level.DEBUG, "deleteTicketById completed successfully");
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transaction.end();
        }
        return flag;
    }

    public static int numberTicketsRemaining(long id) throws ServiceException {
        int number;
        TicketDao ticketDao = new TicketDao();
        EntityTransaction transaction = new EntityTransaction();
        try {
            transaction.begin(ticketDao);
            number = ticketDao.numberTicketsRemaining(id);
            logger.log(Level.DEBUG, "numberTicketsRemaining completed successfully");
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transaction.end();
        }
        return number;
    }
}
