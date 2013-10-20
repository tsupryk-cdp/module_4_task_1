package com.tsupryk.api;

import com.tsupryk.api.Ticket;
import com.tsupryk.api.TicketCategory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
