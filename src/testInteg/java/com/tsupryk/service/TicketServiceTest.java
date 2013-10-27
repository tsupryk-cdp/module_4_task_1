package com.tsupryk.service;

import com.tsupryk.api.ITicketService;
import com.tsupryk.api.IUserService;
import com.tsupryk.api.ServiceRuntimeException;
import com.tsupryk.api.entity.Ticket;
import com.tsupryk.api.entity.TicketCategory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * The class TicketServiceTest.
 * <p/>
 * User: Vitaliy
 * Date: 27.10.13
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-test-context.xml"})
public class TicketServiceTest {

    private static final int INITIAL_TICKETS_AMOUNT = 20;

    @Autowired
    private ITicketService service;

    @Autowired
    private IUserService userService;

    private Integer userId;

    @Before
    public void setUp() {
        service.initTickets();
        userService.init();
        userId = userService.getAllUsers().get(0).getId();
    }

    @Test
    public void getAvailableTicketsTest() {
        int size = service.getAvailableTickets(null, null, null).size();
        Assert.assertTrue(size == INITIAL_TICKETS_AMOUNT);
    }

    @Test
    public void bookTicketsTest() {
        List<Ticket> tickets = service.getAvailableTickets("Terminator", null, null);
        service.bookTickets(userService.getAllUsers().get(0).getId(), tickets);

        int availableTicketsCount = service.getAvailableTickets(null, null, TicketCategory.STANDARD).size();
        int expectedTicketsCount = INITIAL_TICKETS_AMOUNT - tickets.size();

        Assert.assertTrue(availableTicketsCount == expectedTicketsCount);
    }

    @Test
    public void getBookedTicketsTest() {
        List<Ticket> ticketsToBook = service.getAvailableTickets("Terminator", null, null);
        service.bookTickets(userId, ticketsToBook);

        List<Ticket> bookedTickets = service.getBookedTickets(userId, "Terminator", null, null);
        for (Ticket t : bookedTickets) {
            Assert.assertTrue(t.getUser().getId().equals(userId));
        }
        int bookedTicketsCount = bookedTickets.size();
        int expectedTicketsCount = ticketsToBook.size();

        Assert.assertTrue(bookedTicketsCount == expectedTicketsCount);
    }

    @Test(expected = ServiceRuntimeException.class)
    public void bookTicketsExceptionTest() {
        List<Ticket> ticketsToBook = service.getAvailableTickets("Terminator", null, null);
        service.bookTickets(userId, ticketsToBook);
        service.bookTickets(userId, ticketsToBook);
    }

    @Test(expected = ServiceRuntimeException.class)
    public void getBookedTicketsExceptionTest() {
        service.getBookedTickets(-1, "Terminator", null, null);
    }
}
