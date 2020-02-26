package com.liukhtenko.ticket.service.impl;

import com.liukhtenko.ticket.dao.EntityTransaction;
import com.liukhtenko.ticket.dao.impl.EventDao;
import com.liukhtenko.ticket.entity.Event;
import com.liukhtenko.ticket.exception.DaoException;
import com.liukhtenko.ticket.exception.ServiceException;
import com.liukhtenko.ticket.service.EventServiceInterface;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
/**
 * Class that management events.
 *
 * @author Vladimir Liukhtenko
 * @version 1.25 02 Feb 2020
 */
public class EventService implements EventServiceInterface {
    static Logger logger = LogManager.getLogger();

    public List<Event> findAllEvents() throws ServiceException {
        List<Event> events;
        EventDao eventDao = new EventDao();
        EntityTransaction transaction = new EntityTransaction();
        try {
            transaction.begin(eventDao);
            events = eventDao.findAll();
            logger.log(Level.DEBUG, "findAllEvents completed successfully");
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transaction.end();
        }
        return events;
    }

    public List<Event> findEventByType(String type) throws ServiceException {
        List<Event> events;
        EventDao eventDao = new EventDao();
        EntityTransaction transaction = new EntityTransaction();
        try {
            transaction.begin(eventDao);
            events = eventDao.findByType(type);
            logger.log(Level.DEBUG, "findEventByType completed successfully");
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transaction.end();
        }
        return events;
    }

    public Event findEventById(Long id) throws ServiceException {
        Event event;
        EventDao eventDao = new EventDao();
        EntityTransaction transaction = new EntityTransaction();
        try {
            transaction.begin(eventDao);
            event = eventDao.findById(id);
            logger.log(Level.DEBUG, "findEventById completed successfully");
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transaction.end();
        }
        return event;
    }

    public boolean createEvent(Event event) throws ServiceException {
        EventDao eventDao = new EventDao();
        EntityTransaction transaction = new EntityTransaction();
        boolean flag;
        try {
            transaction.begin(eventDao);
            flag = eventDao.create(event);
            logger.log(Level.DEBUG, "createEvent completed successfully");
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transaction.end();
        }
        return flag;
    }

    public void deleteEventById(long id) throws ServiceException {
        EventDao eventDao = new EventDao();
        EntityTransaction transaction = new EntityTransaction();
        try {
            transaction.begin(eventDao);
            eventDao.delete(id);
            logger.log(Level.DEBUG, "deleteEventById completed successfully");
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transaction.end();
        }
    }

    public void updateEvent(Event event) throws ServiceException {
        EventDao eventDao = new EventDao();
        EntityTransaction transaction = new EntityTransaction();
        try {
            transaction.begin(eventDao);
            eventDao.update(event);
            logger.log(Level.DEBUG, "updateEvent completed successfully");
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transaction.end();
        }
    }
}
