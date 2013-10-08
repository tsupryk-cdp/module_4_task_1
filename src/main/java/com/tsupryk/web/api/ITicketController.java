package com.tsupryk.web.api;

import com.tsupryk.api.ITicket;
import com.tsupryk.api.TicketCategory;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;

/**
 * The Interface ITicketController.
 * Date: 06.09.13
 *
 */
public interface ITicketController {

    public Object getAvailableTickets(ModelAndView modelAndView, String filmName, Date filmStartDate, TicketCategory ticketCategory);

    public Object bookTickets(String userId, List<ITicket> ticketList);

    public Object getBookedTickets(String userId, String filmName, Date filmStartDate, TicketCategory ticketCategory);
}
