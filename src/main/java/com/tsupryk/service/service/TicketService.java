package com.tsupryk.service.service;

import com.tsupryk.api.ITicket;
import com.tsupryk.api.ServiceRuntimeException;
import com.tsupryk.api.TicketCategory;
import com.tsupryk.api.TicketStatus;
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
        if (!isEmpty(filmName) || !isEmpty(filmStartDate) || !isEmpty(ticketCategory)) {
            IFiltrable filter = FilterBuilder.buildFreeTicketsFilter(filmName, filmStartDate, ticketCategory);
            List<ITicket> tickets = ticketRepository.getTickets(filter);
            Collections.sort(tickets, ticketComparator);
            return tickets;
        }
        return null;
    }

    @Override
    public boolean bookTickets(String userId, List<ITicket> ticketList) {
        validateTickets(ticketList);
        for (ITicket ticket : ticketList) {
            ITicket storedTicket = ticketRepository.getById(ticket.getId());
            storedTicket.setStatus(TicketStatus.BOOKED);
            ticketRepository.updateTicket(storedTicket);
        }
        return true;
    }

    @Override
    public List<ITicket> getBookedTickets(String userId, String filmName, Date filmStartDate,
                  TicketCategory ticketCategory) {
        if (!isEmpty(userId) || !isEmpty(filmName) || !isEmpty(filmStartDate) || !isEmpty(ticketCategory)) {
            IFiltrable filter = FilterBuilder.buildBookedTicketsFilter(userId, filmName, filmStartDate, ticketCategory);
            List<ITicket> tickets = ticketRepository.getTickets(filter);
            Collections.sort(tickets, ticketComparator);
            return tickets;
        }
        return null;
    }

    private void validateTickets(List<ITicket> ticketList) {
        for (ITicket ticket : ticketList) {
            checkField(ticket.getId(), "Wrong or empty field Id.");
            checkField(ticket.getCategory(), "Wrong or empty field Category.");
        }
    }

    private void checkField(Object value, String message) {
        if (isEmpty(value)) {
            throw new ServiceRuntimeException(new IllegalArgumentException(message));
        }
    }

    private boolean isEmpty(Object value) {
        return value instanceof String ? StringUtils.isEmpty(value) : value == null;
    }

    private Comparator<ITicket> ticketComparator = new Comparator<ITicket>() {
        @Override
        public int compare(ITicket o1, ITicket o2) {
            return o1.getPlaceNumber() - o2.getPlaceNumber();
        }
    };
}
