package com.liukhtenko.ticket.service;

import com.liukhtenko.ticket.entity.Event;
import com.liukhtenko.ticket.exception.ServiceException;

import java.util.List;

public interface EventServiceInterface {
    List<Event> findEventByType(String type) throws ServiceException;
    boolean createEvent(Event event) throws ServiceException;
    void deleteEventById(long id) throws ServiceException;
    void updateEvent(Event event) throws ServiceException;

}
