package com.tsupryk.service.service;

import com.tsupryk.api.ITicket;
import com.tsupryk.api.TicketCategory;
import com.tsupryk.repository.api.IFiltrable;
import com.tsupryk.repository.api.ITicketRepository;
import com.tsupryk.service.api.ITicketService;
import com.tsupryk.service.util.FilterBuilder;
import org.codehaus.jackson.map.ObjectMapper;
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

    @Autowired
    private ObjectMapper jacksonMarshaller;


    @Override
    public List<ITicket> getAvailableTickets(String filmName, Date filmStartDate, TicketCategory ticketCategory) {
        if (!isEmpty(filmName) || !isEmpty(filmStartDate) || !isEmpty(ticketCategory)) {
            IFiltrable filter = FilterBuilder.buildFreeTicketsFilter(filmName, filmStartDate, ticketCategory);
            return ticketRepository.getTickets(filter);
        }
        return null;
    }

    @Override
    public boolean bookTickets(String userId, List<ITicket> ticketList) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<ITicket> getBookedTickets(String userId, String filmName, Date filmStartDate,
                  TicketCategory ticketCategory) {
        if (!isEmpty(userId) || !isEmpty(filmName) || !isEmpty(filmStartDate) || !isEmpty(ticketCategory)) {
            IFiltrable filter = FilterBuilder.buildBookedTicketsFilter(userId, filmName, filmStartDate, ticketCategory);
            return ticketRepository.getTickets(filter);
        }
        return null;
    }

    private boolean isEmpty(String value) {
        return StringUtils.isEmpty(value);
    }

    private boolean isEmpty(Object value) {
        return value == null;
    }
}
