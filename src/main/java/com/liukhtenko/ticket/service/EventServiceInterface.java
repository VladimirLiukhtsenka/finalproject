package com.liukhtenko.ticket.service;

        import com.liukhtenko.ticket.entity.Event;
        import com.liukhtenko.ticket.exception.ServiceException;

        import java.util.List;
/**
 * Interface for
 * {@link com.liukhtenko.ticket.service.impl.EventService}
 *
 * @author Vladimir Liukhtenko
 * @version 1.25 02 Feb 2020
 */
public interface EventServiceInterface {
    List<Event> findEventByType(String type) throws ServiceException;

    boolean createEvent(Event event) throws ServiceException;

    void deleteEventById(long id) throws ServiceException;

    void updateEvent(Event event) throws ServiceException;

    List<Event> findAllEvents() throws ServiceException;


}
