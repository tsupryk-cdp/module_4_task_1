package com.tsupryk.web;

import com.tsupryk.api.*;
import com.tsupryk.api.ITicketService;
import com.tsupryk.util.TicketUtil;
import com.tsupryk.api.IJsonTicketController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * The Class JsonController.
 * Date: 06.09.13
 */
@Controller
@RequestMapping("/cinema")
public class JsonTicketController implements IJsonTicketController {

    private static final String USER_ID = "userId";
    private static final String SUCCESS = "SUCCESS";
    private static final String FAIL = "FAIL";
    private static final String EMPTY_FILTER_FIELDS = "You should sprecify at least one parameter: filmName, filmStartDate or ticketCategory";

    @Autowired
    private ITicketService ticketService;

    @Override
    @ResponseBody
    @RequestMapping(value = "/tickets.json", produces = "application/json", method = RequestMethod.GET)
    public Object getAvailableTickets(@RequestParam(required = false) String filmName,
                                      @RequestParam(required = false) Date filmStartDate,
                                      @RequestParam(required = false) TicketCategory ticketCategory) {

        if (TicketUtil.isEmpty(filmName) && TicketUtil.isEmpty(filmStartDate) && TicketUtil.isEmpty(ticketCategory)) {
            return new RestResponse(FAIL, EMPTY_FILTER_FIELDS);
        }
        List<Ticket> availableTickets = ticketService.getAvailableTickets(filmName, filmStartDate, ticketCategory);
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
    public Object bookTickets(@RequestParam Integer userId, @RequestBody List<Ticket> ticketList) {
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
    public Object getBookedTicketsJson(@PathVariable(USER_ID) Integer userId,
                                       @RequestParam(required = false) String filmName,
                                       @RequestParam(required = false) Date filmStartDate,
                                       @RequestParam(required = false) TicketCategory ticketCategory) {
        if (TicketUtil.isEmpty(userId) && TicketUtil.isEmpty(filmName) && TicketUtil.isEmpty(filmStartDate)
                && TicketUtil.isEmpty(ticketCategory)) {
            return new RestResponse(FAIL, EMPTY_FILTER_FIELDS);
        }
        List<Ticket> tickets = ticketService.getBookedTickets(userId, filmName, filmStartDate, ticketCategory);
        RestResponse response = new RestResponse(SUCCESS, tickets);
        return response;
    }

    @Override
    @ResponseBody
    @RequestMapping(value = "/init.json", produces = "application/json", method = RequestMethod.GET)
    public Object initTickets() {
        ticketService.initTickets();
        RestResponse response = new RestResponse(SUCCESS, null);
        return response;
    }

}
