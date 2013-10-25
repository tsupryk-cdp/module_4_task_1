package com.tsupryk.api;

import com.tsupryk.api.entity.Ticket;
import com.tsupryk.api.entity.TicketCategory;

import java.util.Date;
import java.util.List;

/**
 * The Interface IJsonTicketController.
 * Date: 06.09.13
 *
 */
public interface IJsonTicketController {

    public Object bookTickets(Integer  userId, List<Ticket> ticketList);

    public Object getAvailableTickets(String filmName, Date filmStartDate, TicketCategory ticketCategory);

    public Object getBookedTicketsJson(Integer userId, String filmName, Date filmStartDate, TicketCategory ticketCategory);

    Object initTickets();
}
