package com.tsupryk.repository;

import com.tsupryk.api.*;
import com.tsupryk.api.IFiltrable;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 * The Class MemoryTicketRepository.
 * Date: 06.09.13
 */
public class MemoryTicketRepository {

    private Map<String, Ticket> ticketMap;

    private Map<String, List<String>> userTicketIds;

    @Value("${cinema.film.place.count}")
    private Integer placeCount;

    @PostConstruct
    public void init() {
        userTicketIds = new HashMap<>();
        ticketMap = new HashMap<>();
        int count = 0;
        for (int j = 0; j < 2; j++) {
            for (int i = 1; i <= placeCount; i++) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(new Date());
                calendar.add(Calendar.DAY_OF_MONTH, 5 + j);


                Ticket ticket = new Ticket();
                ticket.setId(count++);
                ticket.setStatus(TicketStatus.FREE);
                ticket.setCategory(TicketCategory.STANDARD);
                if (j > 0) {
                    ticket.setFilmName("Terminator");
                } else {
                    ticket.setFilmName("Independence Day");
                }
                ticket.setFilmStartDate(calendar.getTime());
                ticket.setPlaceNumber(i);

                ticketMap.put(ticket.getId().toString(), ticket);
            }
        }
    }

    public List<Ticket> getTickets(IFiltrable filter) {
        List<Ticket> tickets = new ArrayList<>();
        Collection<Ticket> storedTickets = null;
        if (filter.getTicketStatus() == TicketStatus.FREE) {
            storedTickets = ticketMap.values();
        } else if (filter.getTicketStatus() == TicketStatus.BOOKED) {
            storedTickets = new ArrayList<>();
            List<String> userTickets = userTicketIds.get(filter.getUserId());
            if (userTickets != null) {
                for (String ticketId : userTickets) {
                    storedTickets.add(ticketMap.get(ticketId));
                }
            }
        }
        // filter tickets
        for (Ticket ticket : storedTickets) {
            if (ticket.getStatus() == filter.getTicketStatus()) {
                if (filter.getCategory() != null && filter.getCategory() != ticket.getCategory()) {
                    continue;
                }
                if (filter.getFilmName() != null && !filter.getFilmName().equals(ticket.getFilmName())) {
                    continue;
                }
                if (filter.getFilmStartDate() != null && !filter.getFilmStartDate().equals(ticket.getFilmStartDate())) {
                    continue;
                }
                tickets.add(ticket);
            }
        }
        return tickets;
    }

    public boolean updateTicket(Ticket ticket) {
        if (ticketMap.get(ticket.getId()) == null){
            return false;
        }
        ticketMap.put(ticket.getId().toString(), ticket);
        return true;
    }

    public boolean bookTicket(String userId, Ticket ticket) {
        validateTicketId(ticket.getId().toString());
        List<String> ticketIds = userTicketIds.get(userId);
        if (ticketIds == null) {
            ticketIds = new ArrayList<>();
        }
        ticketIds.add(ticket.getId().toString());
        userTicketIds.put(userId, ticketIds);
        // update ticket status and category
        Ticket storedTicket = ticketMap.get(ticket.getId());
        storedTicket.setStatus(TicketStatus.BOOKED);
        storedTicket.setCategory(ticket.getCategory());
        updateTicket(storedTicket);
        return true;
    }

    private void validateTicketId(String id) {
        if (ticketMap.get(id) == null){
            throw new ServiceRuntimeException("There is no ticket with id = " + id);
        }
        if (ticketMap.get(id).getStatus() == TicketStatus.BOOKED) {
            throw new ServiceRuntimeException("Ticket with id = " + id + " is already booked");
        }
    }

    public Ticket getById(String id) {
        return ticketMap.get(id);
    }

}
