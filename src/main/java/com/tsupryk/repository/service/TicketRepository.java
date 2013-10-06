package com.tsupryk.repository.service;

import com.tsupryk.api.ITicket;
import com.tsupryk.repository.api.IFiltrable;
import com.tsupryk.repository.api.ITicketRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * The Class TicketRepository.
 * Date: 06.09.13
 */
@Repository
public class TicketRepository implements ITicketRepository {

//    @Value("${json.repository.filepath}")
//    private String filePath;

    private Map<String, ITicket> availableTickets;
    private Map<String, ITicket> bookedTickets;



    @Override
    public List<ITicket> getTickets(IFiltrable filter) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
