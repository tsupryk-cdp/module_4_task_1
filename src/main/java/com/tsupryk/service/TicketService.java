package com.tsupryk.service;

import com.tsupryk.api.*;
import com.tsupryk.api.IFiltrable;
import com.tsupryk.api.ITicketRepository;
import com.tsupryk.api.ITicketService;
import com.tsupryk.util.FilterBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * The Class JsonService.
 * Date: 06.09.13
 */
@Service
public class TicketService implements ITicketService {

    @Autowired
    private ITicketRepository ticketRepository;

    @Override
    @Transactional
    public List<Ticket> getAvailableTickets(String filmName, Date filmStartDate, TicketCategory ticketCategory) {
        IFiltrable filter = FilterBuilder.buildFreeTicketsFilter(filmName, filmStartDate, ticketCategory);
        List<Ticket> tickets = ticketRepository.getTickets(filter);
        Collections.sort(tickets, ticketPlaceComparator);
        return tickets;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public boolean bookTickets(Integer userId, List<Ticket> ticketList) {
        List<Ticket> storedTickets = new ArrayList<>();
        for (Ticket ticket : ticketList) {
            Ticket storedTicket = ticketRepository.getById(ticket.getId());
            if(storedTicket == null) {
                throw new ServiceRuntimeException("The ticket with id = " + ticket.getId() + " doesn't exist.");
            } else if (storedTicket.getStatus() == TicketStatus.BOOKED){
                throw new ServiceRuntimeException("The ticket with id = " + ticket.getId() + " is already booked.");
            }
            else {
                // updating necessary fields
                storedTicket.setCategory(ticket.getCategory());
                storedTicket.setStatus(TicketStatus.BOOKED);
                storedTicket.setUserId(userId);
                storedTickets.add(storedTicket);
            }
        }
        for (Ticket ticket : storedTickets) {
            ticketRepository.updateTicket(ticket);
        }
        return true;
    }

    //TODO @Transactional ???
    @Override
    @Transactional
    public List<Ticket> getBookedTickets(Integer userId, String filmName, Date filmStartDate,
                                         TicketCategory ticketCategory) {
        IFiltrable filter = FilterBuilder.buildBookedTicketsFilter(userId, filmName, filmStartDate, ticketCategory);
        List<Ticket> tickets = ticketRepository.getTickets(filter);
        Collections.sort(tickets, ticketPlaceComparator);
        return tickets;
    }

    //TODO @Transactional ???
    @Override
    @Transactional
    public boolean initTickets() {
        ticketRepository.initTickets();
        return true;
    }

    private Comparator<Ticket> ticketPlaceComparator = new Comparator<Ticket>() {
        @Override
        public int compare(Ticket o1, Ticket o2) {
            return o1.getPlaceNumber() - o2.getPlaceNumber();
        }
    };
}
