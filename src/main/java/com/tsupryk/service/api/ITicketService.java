package com.tsupryk.service.api;


import com.tsupryk.api.ITicket;
import com.tsupryk.api.Ticket;
import com.tsupryk.api.TicketCategory;

import java.util.Date;
import java.util.List;

/**
 * The Class ITicketService.
 * Date: 06.09.13
 */
public interface ITicketService {

    public List<ITicket> getAvailableTickets(String filmName, Date filmStartDate, TicketCategory ticketCategory);

    public boolean bookTickets(Integer userId, List<Ticket> ticketList);

    public List<ITicket> getBookedTickets(Integer userId, String filmName, Date filmStartDate, TicketCategory ticketCategory);

}
