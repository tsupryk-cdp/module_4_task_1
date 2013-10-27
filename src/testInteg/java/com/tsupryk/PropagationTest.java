package com.tsupryk;

import com.tsupryk.api.entity.Ticket;
import com.tsupryk.impl.TestTicketRepository;
import com.tsupryk.impl.TestTicketService;
import org.junit.After;
import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * The class PropagationTest.
 * <p/>
 * User: Vitaliy
 * Date: 16.10.13
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-test-context.xml"})
public class PropagationTest {

    @Autowired
    TestTicketRepository repository;

    @Autowired
    TestTicketService service;

    @After
    public void after() {
        repository.deleteAll();
    }

    /**
     * Both repository and service has propagation = Propagation.REQUIRES_NEW.
     * Repository fails, service not.
     * Hence there should be only 2 tickets, inserted in service.
     */
    @Test
    @Ignore
    public void testBothRequiresNewFallInner() {
        try {
            service.insertRequiresNew(false, true);
        } catch (RuntimeException ex) {

        }
        List<Ticket> tickets = repository.selectAll();
        assertTickets(tickets, 2);
    }

    /**
     * Both repository and service has propagation = Propagation.REQUIRES_NEW.
     * Service fails, repository not.
     * Hence there should be only 1 ticket, inserted in repository.
     */
    @Test
    @Ignore
    public void testRequiresNewFallOuter() {
        try {
            service.insertRequiresNew(true, false);
        } catch (RuntimeException ex) { }
        List<Ticket> tickets = repository.selectAll();
        assertTickets(tickets, 1);
    }

    /**
     * Both repository and service has propagation = Propagation.REQUIRED.
     * Repository fails, service not.
     * Hence there should be no tickets because 2 calls results in one transaction.
     */
    @Test
    @Ignore
    public void testBothRequiredFallInner() {
        // falls repository
        try {
            service.insertRequired(false, true);
        } catch (RuntimeException ex) { }
        List<Ticket> tickets = repository.selectAll();
        assertTrue(tickets.size() == 0);
    }

    private void assertTickets(List<Ticket> tickets, int size) {
        showTickets(tickets);
        assertTrue(tickets.size() == size);
        for (Ticket t : tickets) {
            assertNotNull(t);
        }
    }

    private void showTickets(List<Ticket> tickets) {
        if (tickets.size() == 0) {
            System.out.println("Empty!");
            return;
        }
        for (Ticket t : tickets) {
            System.out.println("Ticket: " + t.getId() + ", " + t.getFilmName());
        }
    }


}
