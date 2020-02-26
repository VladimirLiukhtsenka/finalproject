package com.liukhtenko.ticket.service.impl;

import com.liukhtenko.ticket.entity.Event;
import com.liukhtenko.ticket.entity.TypeEvent;
import com.liukhtenko.ticket.exception.ServiceException;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
/**
 * The class that testing
 * @see EventService
 *
 * @author Vladimir Liukhtenko
 * @version 1.25 02 Feb 2020
 */
public class EventServiceTest {
    private EventService eventService;

    @BeforeMethod
    public void setUp() {
        eventService = new EventService();
    }

    @AfterMethod
    public void tearDown() {
        eventService = null;
    }

    @Test
    public void testFindAllEvents() throws ServiceException {
        List<Event> events = eventService.findAllEvents();
        Assert.assertTrue(events.size() > 0);
    }

    @Test
    public void testFindEventByType() throws ServiceException {
        List<Event> events = eventService.findEventByType(TypeEvent.SPORT.getValue());
        Assert.assertTrue(events.size() > 0);
    }
}