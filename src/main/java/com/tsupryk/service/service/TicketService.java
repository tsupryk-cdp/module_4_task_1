package com.tsupryk.service.service;

import com.tsupryk.api.*;
import com.tsupryk.repository.api.IFiltrable;
import com.tsupryk.repository.api.ITicketRepository;
import com.tsupryk.service.api.ITicketService;
import com.tsupryk.service.util.FilterBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public List<Ticket> getAvailableTickets(String filmName, Date filmStartDate, TicketCategory ticketCategory) {
        IFiltrable filter = FilterBuilder.buildFreeTicketsFilter(filmName, filmStartDate, ticketCategory);
        List<Ticket> tickets = ticketRepository.getTickets(filter);
        Collections.sort(tickets, ticketPlaceComparator);
        return tickets;
    }

    @Override
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

    @Override
    public List<Ticket> getBookedTickets(Integer userId, String filmName, Date filmStartDate,
                                         TicketCategory ticketCategory) {
        IFiltrable filter = FilterBuilder.buildBookedTicketsFilter(userId, filmName, filmStartDate, ticketCategory);
        List<Ticket> tickets = ticketRepository.getTickets(filter);
        Collections.sort(tickets, ticketPlaceComparator);
        return tickets;
    }

    private Comparator<Ticket> ticketPlaceComparator = new Comparator<Ticket>() {
        @Override
        public int compare(Ticket o1, Ticket o2) {
            return o1.getPlaceNumber() - o2.getPlaceNumber();
        }
    };
}
