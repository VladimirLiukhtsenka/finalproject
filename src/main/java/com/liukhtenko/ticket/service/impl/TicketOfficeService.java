package com.liukhtenko.ticket.service.impl;

import com.liukhtenko.ticket.dao.EntityTransaction;
import com.liukhtenko.ticket.dao.impl.TicketOfficeDao;
import com.liukhtenko.ticket.dao.impl.UserDao;
import com.liukhtenko.ticket.entity.TicketOffice;
import com.liukhtenko.ticket.entity.User;
import com.liukhtenko.ticket.exception.DaoException;
import com.liukhtenko.ticket.exception.ServiceException;
import com.liukhtenko.ticket.service.TicketOfficeInterface;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class TicketOfficeService implements TicketOfficeInterface {
    static Logger logger = LogManager.getLogger();

    public List<TicketOffice> findAllTicketOffice() throws ServiceException {
        List<TicketOffice> ticketOffices;
        TicketOfficeDao ticketOfficeDao = new TicketOfficeDao();
        EntityTransaction transaction = new EntityTransaction();
        try {
            transaction.begin(ticketOfficeDao);
            ticketOffices = ticketOfficeDao.findAll();
            transaction.commit();
            logger.log(Level.DEBUG, "findAllTicketOffice completed successfully");
        } catch (DaoException e) {
            transaction.rollback();
            throw new ServiceException(e);
        } finally {
            transaction.end();
        }
        return ticketOffices;
    }
    public void deleteTicketOfficeByPhone(String phone) throws ServiceException {
        TicketOfficeDao ticketOfficeDao = new TicketOfficeDao();
        EntityTransaction transaction = new EntityTransaction();
        try {
            transaction.begin(ticketOfficeDao);
            ticketOfficeDao.delete(phone);
            transaction.commit();
            logger.log(Level.DEBUG, "deleteTicketOfficeByPhone completed successfully");
        } catch (DaoException e) {
            transaction.rollback();
            throw new ServiceException(e);
        } finally {
            transaction.end();
        }
    }
    public boolean createTicketOffice(TicketOffice ticketOffice) throws ServiceException {
        TicketOfficeDao ticketOfficeDao = new TicketOfficeDao();
        EntityTransaction transaction = new EntityTransaction();
        boolean flag;
        try {
            transaction.begin(ticketOfficeDao);
            flag = ticketOfficeDao.create(ticketOffice);
            transaction.commit();
            logger.log(Level.DEBUG, "createTicketOffice completed successfully");
        } catch (DaoException e) {
            transaction.rollback();
            throw new ServiceException(e);
        } finally {
            transaction.end();
        }
        return flag;
    }
}
