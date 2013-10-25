package com.tsupryk.service;

import com.tsupryk.api.*;
import com.tsupryk.api.IFiltrable;
import com.tsupryk.api.ITicketRepository;
import com.tsupryk.api.ITicketService;
import com.tsupryk.api.entity.Ticket;
import com.tsupryk.api.entity.TicketCategory;
import com.tsupryk.api.entity.TicketStatus;
import com.tsupryk.api.entity.User;
import com.tsupryk.util.FilterBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.List;

/**
 * The Class JsonService.
 * Date: 06.09.13
 */
@Service
@Transactional
public class TicketService implements ITicketService {

    @Autowired
    private ITicketRepository ticketRepository;

    @Autowired
    private IUserRepository userRepository;

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
        User user = userRepository.getById(userId);
        if (user == null) {
            throw new ServiceRuntimeException("The user with id = " + userId + " doesn't exist.");
        }
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
                storedTickets.add(storedTicket);
                storedTicket.setUser(user);
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
        if (userRepository.getById(userId) == null) {
            throw new ServiceRuntimeException("There is no user with id = " + userId);
        }
        IFiltrable filter = FilterBuilder.buildBookedTicketsFilter(userId, filmName, filmStartDate, ticketCategory);
        List<Ticket> tickets = ticketRepository.getTickets(filter);
        for (Ticket t : tickets) {
            t.getUser().setUserTickets(null);
        }
        Collections.sort(tickets, ticketPlaceComparator);
        return tickets;
    }

    @Override
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
