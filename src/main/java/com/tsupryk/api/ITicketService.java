package com.tsupryk.api;


import com.tsupryk.api.Ticket;
import com.tsupryk.api.TicketCategory;

import java.util.Date;
import java.util.List;

/**
 * The Class ITicketService.
 * Date: 06.09.13
 */
public interface ITicketService {

    public List<Ticket> getAvailableTickets(String filmName, Date filmStartDate, TicketCategory ticketCategory);

    public boolean bookTickets(Integer userId, List<Ticket> ticketList);

    public List<Ticket> getBookedTickets(Integer userId, String filmName, Date filmStartDate, TicketCategory ticketCategory);

}
