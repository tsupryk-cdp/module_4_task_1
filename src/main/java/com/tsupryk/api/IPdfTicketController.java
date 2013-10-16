package com.tsupryk.api;

import com.tsupryk.api.Ticket;
import com.tsupryk.api.TicketCategory;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;

/**
 * The Interface IJsonTicketController.
 * Date: 06.09.13
 *
 */
public interface IPdfTicketController {

    public ModelAndView getAvailableTickets(String filmName, Date filmStartDate, TicketCategory ticketCategory);

    public ModelAndView getBookedTickets(Integer userId, String filmName, Date filmStartDate, TicketCategory ticketCategory);

    public ModelAndView bookTickets(Integer userId, List<Ticket> ticketList);

}
