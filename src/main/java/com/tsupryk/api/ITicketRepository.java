package com.tsupryk.api;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * The Interface ITicketRepository.
 * Date: 06.09.13
 */
public interface ITicketRepository {

    public List<Ticket> getTickets(IFiltrable filter);

    public boolean updateTicket(Ticket ticket);

    public Ticket getById(Integer id);

    void initTickets();
}
