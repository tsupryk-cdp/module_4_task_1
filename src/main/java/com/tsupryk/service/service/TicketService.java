package com.tsupryk.service.service;

import com.tsupryk.api.*;
import com.tsupryk.repository.api.IFiltrable;
import com.tsupryk.repository.api.ITicketRepository;
import com.tsupryk.service.api.ITicketService;
import com.tsupryk.service.util.FilterBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
    public List<ITicket> getAvailableTickets(String filmName, Date filmStartDate, TicketCategory ticketCategory) {
        IFiltrable filter = FilterBuilder.buildFreeTicketsFilter(filmName, filmStartDate, ticketCategory);
        List<ITicket> tickets = ticketRepository.getTickets(filter);
        Collections.sort(tickets, ticketPlaceComparator);
        return tickets;
    }

    @Override
    public boolean bookTickets(Integer userId, List<Ticket> ticketList) {
        List<ITicket> storedTickets = new ArrayList<>();
        for (ITicket ticket : ticketList) {
            ITicket storedTicket = ticketRepository.getById(ticket.getId());
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
        for (ITicket ticket : storedTickets) {
            ticketRepository.updateTicket(ticket);
        }
        return true;
    }

    @Override
    public List<ITicket> getBookedTickets(Integer userId, String filmName, Date filmStartDate,
                  TicketCategory ticketCategory) {
        IFiltrable filter = FilterBuilder.buildBookedTicketsFilter(userId, filmName, filmStartDate, ticketCategory);
        List<ITicket> tickets = ticketRepository.getTickets(filter);
        Collections.sort(tickets, ticketPlaceComparator);
        return tickets;
    }

    private Comparator<ITicket> ticketPlaceComparator = new Comparator<ITicket>() {
        @Override
        public int compare(ITicket o1, ITicket o2) {
            return o1.getPlaceNumber() - o2.getPlaceNumber();
        }
    };
}
