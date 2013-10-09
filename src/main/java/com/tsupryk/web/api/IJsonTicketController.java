package com.tsupryk.web.api;

import com.tsupryk.api.ITicket;
import com.tsupryk.api.TicketCategory;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;

/**
 * The Interface IJsonTicketController.
 * Date: 06.09.13
 *
 */
public interface IJsonTicketController {

    public Object bookTickets(String userId, List<ITicket> ticketList);

    Object getAvailableTickets(ModelAndView modelAndView, String filmName, Date filmStartDate, TicketCategory ticketCategory);

    Object getBookedTicketsJson( String userId, String filmName, Date filmStartDate, TicketCategory ticketCategory);

}
