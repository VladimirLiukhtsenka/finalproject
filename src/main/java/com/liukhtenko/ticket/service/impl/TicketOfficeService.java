package com.liukhtenko.ticket.service.impl;

import com.liukhtenko.ticket.dao.EntityTransaction;
import com.liukhtenko.ticket.dao.impl.TicketOfficeDao;
import com.liukhtenko.ticket.entity.TicketOffice;
import com.liukhtenko.ticket.exception.DaoException;
import com.liukhtenko.ticket.exception.ServiceException;
import com.liukhtenko.ticket.service.TicketOfficeInterface;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * Class that management ticket office.
 *
 * @author Vladimir Liukhtenko
 * @version 1.25 02 Feb 2020
 */
public class TicketOfficeService implements TicketOfficeInterface {
    private static Logger logger = LogManager.getLogger();

    /**
     * This method finds all ticket offices
     *
     * @return List TicketOffice
     * @throws ServiceException if happen DaoException
     */
    public List<TicketOffice> findAllTicketOffice() throws ServiceException {
        List<TicketOffice> ticketOffices;
        TicketOfficeDao ticketOfficeDao = new TicketOfficeDao();
        EntityTransaction transaction = new EntityTransaction();
        try {
            transaction.begin(ticketOfficeDao);
            ticketOffices = ticketOfficeDao.findAll();
            logger.log(Level.DEBUG, "findAllTicketOffice completed successfully");
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transaction.end();
        }
        return ticketOffices;
    }

    /**
     * This method delete  ticket office
     *
     * @param phone ticket office phone
     * @throws ServiceException if happen DaoException
     */
    public void deleteTicketOfficeByPhone(String phone) throws ServiceException {
        TicketOfficeDao ticketOfficeDao = new TicketOfficeDao();
        EntityTransaction transaction = new EntityTransaction();
        try {
            transaction.begin(ticketOfficeDao);
            ticketOfficeDao.delete(phone);
            logger.log(Level.DEBUG, "deleteTicketOfficeByPhone completed successfully");
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transaction.end();
        }
    }

    /**
     * This method create  ticket office
     *
     * @param ticketOffice new ticket office
     * @return boolean as a result of the method
     * @throws ServiceException if happen DaoException
     */
    public boolean createTicketOffice(TicketOffice ticketOffice) throws ServiceException {
        TicketOfficeDao ticketOfficeDao = new TicketOfficeDao();
        EntityTransaction transaction = new EntityTransaction();
        boolean flag;
        try {
            transaction.begin(ticketOfficeDao);
            flag = ticketOfficeDao.create(ticketOffice);
            logger.log(Level.DEBUG, "createTicketOffice completed successfully");
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transaction.end();
        }
        return flag;
    }
}
