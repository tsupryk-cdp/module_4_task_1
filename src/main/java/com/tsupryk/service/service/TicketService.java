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
    public boolean bookTickets(String userId, List<Ticket> ticketList) {
        for (ITicket ticket : ticketList) {
            ticketRepository.bookTicket(userId, ticket);
        }
        return true;
    }

    @Override
    public List<ITicket> getBookedTickets(String userId, String filmName, Date filmStartDate,
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
