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
    private static Logger logger = LogManager.getLogger();

    /**
     * This method finds all events
     *
     * @return List Event
     * @throws ServiceException if happen DaoException
     */
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

    /**
     * This method finds all events by event type
     *
     * @param type event type
     * @return List Event
     * @throws ServiceException if happen DaoException
     */
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

    /**
     * This method finds event by event id
     *
     * @param id event id
     * @return Event
     * @throws ServiceException if happen DaoException
     */
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

    /**
     * This method create event
     *
     * @param event new event
     * @return boolean as a result of the method
     * @throws ServiceException if happen DaoException
     */
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

    /**
     * This method delete event by event id
     *
     * @param id event id
     * @throws ServiceException if happen DaoException
     */
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

    /**
     * This method updates the event
     *
     * @param event event to update
     * @throws ServiceException if happen DaoException
     */
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
