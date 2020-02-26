package com.liukhtenko.ticket.service.impl;

import com.liukhtenko.ticket.exception.ServiceException;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
/**
 * The class that testing
 * {@link  com.liukhtenko.ticket.service.impl.TicketService}
 *
 * @author Vladimir Liukhtenko
 * @version 1.25 02 Feb 2020
 */
public class TicketServiceTest {
    private TicketService ticketService;

    @BeforeMethod
    public void setUp() {
        ticketService = new TicketService();
    }

    @AfterMethod
    public void tearDown() {
        ticketService = null;
    }

    @Test
    public void testPrintTickets() throws ServiceException {
        long adminId = 1;
        int adminTickets = 3;
        List<List<String>> list = ticketService.printTickets(adminId);
        Assert.assertEquals(list.size(), adminTickets);
    }
}