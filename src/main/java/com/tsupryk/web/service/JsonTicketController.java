package com.tsupryk.web.service;

import com.tsupryk.api.*;
import com.tsupryk.service.api.ITicketService;
import com.tsupryk.service.util.TicketUtil;
import com.tsupryk.web.api.IJsonTicketController;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The Class JsonController.
 * Date: 06.09.13
 */
@Controller
@RequestMapping("/cinema")
public class JsonTicketController implements IJsonTicketController {

    protected static final String USER_ID = "userId";
    protected static final String SUCCESS = "SUCCESS";
    protected static final String FAIL = "FAIL";

    @Autowired
    private ITicketService ticketService;

    @Override
    @ResponseBody
    @RequestMapping(value = "/tickets.json", produces = "application/json", method = RequestMethod.GET)
    public Object getAvailableTickets(ModelAndView modelAndView, @RequestParam(required = false) String filmName,
                                      @RequestParam(required = false) Date filmStartDate,
                                      @RequestParam(required = false) TicketCategory ticketCategory) {
        if (TicketUtil.isEmpty(filmName) && TicketUtil.isEmpty(filmStartDate) && TicketUtil.isEmpty(ticketCategory)) {
            return new RestResponse(FAIL, "No filter parameters specified");
        }
        List<ITicket> availableTickets = ticketService.getAvailableTickets(filmName, filmStartDate, ticketCategory);
        RestResponse response = new RestResponse(SUCCESS, availableTickets);
        return response;
    }

    /**
     * Books tickets for user.
     *
     * @param userId the user id, required
     * @param ticketList list of tickets to book, field Id in every ticket is required
     * @return the object
     */
    @Override
    @ResponseBody
    @RequestMapping(value = "/book.json", produces = "application/json", method = RequestMethod.POST)
    public Object bookTickets(@RequestParam String userId, @RequestBody List<Ticket> ticketList) {
        RestResponse response = null;
        try {
            TicketUtil.validateTickets(ticketList);
            boolean result = ticketService.bookTickets(userId, ticketList);
            if (result) {
                response = new RestResponse(SUCCESS, null);
            } else {
                response = new RestResponse(FAIL, null);
            }
        } catch (ServiceRuntimeException e) {
            response = new RestResponse(FAIL, e.getMessage());
        }
        return response;
    }

    @Override
    @ResponseBody
    @RequestMapping(value = "/tickets/booked/{" + USER_ID + "}.json", produces = "application/json",
            method = RequestMethod.GET)
    public Object getBookedTicketsJson(@PathVariable(USER_ID) String userId,
                                       @RequestParam(required = false) String filmName,
                                       @RequestParam(required = false) Date filmStartDate,
                                       @RequestParam(required = false) TicketCategory ticketCategory) {
        if (TicketUtil.isEmpty(userId) && TicketUtil.isEmpty(filmName) && TicketUtil.isEmpty(filmStartDate)
                && TicketUtil.isEmpty(ticketCategory)) {
            return new RestResponse(FAIL, "No filter parameters specified");
        }
        List<ITicket> tickets = ticketService.getBookedTickets(userId, filmName, filmStartDate, ticketCategory);
        RestResponse response = new RestResponse(SUCCESS, tickets);
        return response;
    }

}
