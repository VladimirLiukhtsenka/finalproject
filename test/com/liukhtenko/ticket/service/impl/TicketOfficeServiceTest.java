package com.liukhtenko.ticket.service.impl;

import com.liukhtenko.ticket.entity.TicketOffice;
import com.liukhtenko.ticket.exception.ServiceException;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
/**
 * The class that testing
 * @see TicketOfficeService
 *
 * @author Vladimir Liukhtenko
 * @version 1.25 02 Feb 2020
 */
public class TicketOfficeServiceTest {
    private TicketOfficeService ticketOfficeService;

    @BeforeMethod
    public void setUp() {
        ticketOfficeService = new TicketOfficeService();
    }

    @AfterMethod
    public void tearDown() {
        ticketOfficeService = null;
    }

    @Test
    public void testFindAllTicketOffice() throws ServiceException {
        List<TicketOffice> ticketOffices = ticketOfficeService.findAllTicketOffice();
        int actual = 8;
        int expected = ticketOffices.size();
        Assert.assertEquals(expected, actual);
    }
}