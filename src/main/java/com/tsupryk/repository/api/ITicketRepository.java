package com.tsupryk.repository.api;

import com.tsupryk.api.ITicket;

import java.util.List;

/**
 * The Interface ITicketRepository.
 * Date: 06.09.13
 */
public interface ITicketRepository {

    public List<ITicket> getTickets(IFiltrable filter);

    public boolean updateTicket(ITicket ticket);

    public ITicket getById(Integer id);

}
