package com.tsupryk.repository.service;

import com.tsupryk.api.ITicket;
import com.tsupryk.api.Ticket;
import com.tsupryk.api.TicketCategory;
import com.tsupryk.api.TicketStatus;
import com.tsupryk.repository.api.IFiltrable;
import com.tsupryk.repository.api.ITicketRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 * The Class TicketRepository.
 * Date: 06.09.13
 */
@Repository
public class TicketRepository implements ITicketRepository {

    private Map<String, ITicket> ticketMap;

    private Map<String, List<String>> userTicketIds;

    @PostConstruct
    public void init() {
        userTicketIds = new HashMap<>();
        ticketMap = new HashMap<>();
        int count = 0;
        for (int j = 0; j < 2; j++) {
            for (int i = 1; i <= 10; i++) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(new Date());
                calendar.add(Calendar.DAY_OF_MONTH, 5 + j);


                ITicket ticket = new Ticket();
                ticket.setId(String.valueOf(count++));
                ticket.setStatus(TicketStatus.FREE);
                ticket.setCategory(TicketCategory.STANDARD);
                if (j > 0) {
                    ticket.setFilmName("Terminator");
                } else {
                    ticket.setFilmName("Independence Day");
                }
                ticket.setFilmStartDate(calendar.getTime());
                ticket.setPlaceNumber(i);

                ticketMap.put(ticket.getId(), ticket);
            }
        }
    }

    @Override
    public List<ITicket> getTickets(IFiltrable filter) {
        List<ITicket> tickets = new ArrayList<>();
        Collection<ITicket> storedTickets = null;
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
        for (ITicket ticket : storedTickets) {
            if (ticket.getStatus() == TicketStatus.FREE) {
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

    @Override
    public boolean updateTicket(ITicket ticket) {
        if(ticketMap.get(ticket.getId()) == null){
            return false;
        }
        ticketMap.put(ticket.getId(), ticket);
        return true;
    }

    @Override
    public ITicket getById(String id) {
        return ticketMap.get(id);
    }

}
