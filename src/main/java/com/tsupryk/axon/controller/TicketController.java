package com.tsupryk.axon.controller;

import com.tsupryk.api.RestResponse;
import com.tsupryk.api.entity.Ticket;
import com.tsupryk.api.entity.TicketCategory;
import com.tsupryk.axon.aggregates.TicketAR;
import com.tsupryk.axon.commands.BookTicketCommand;
import com.tsupryk.axon.commands.CreateTicketCommand;
import com.tsupryk.axon.listeners.TicketEventListener;
import com.tsupryk.axon.service.TicketService;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * The class UserController.
 * <p/>
 * Date: 17.11.13
 * <p/>
 * Author: Vitaliy
 */
@Controller
@RequestMapping("cinema")
public class TicketController {

    private static final String SUCCESS = "SUCCESS";
    private static final String FAIL = "FAIL";

    private static final String USER_ID = "userId";

    @Autowired
    private CommandGateway commandGateway;

    @Autowired
    private TicketService service;

    @ResponseBody
    @RequestMapping(value = "/createTicket.json", produces = "application/json", method = RequestMethod.POST)
    public Object createTicket(@RequestBody CreateTicketCommand command) {

//        String id = UUID.randomUUID().toString();
//        command.setUserId(id);

        commandGateway.send(command);

        RestResponse response = new RestResponse(SUCCESS, null);

        return response;
    }

    @ResponseBody
    @RequestMapping(value = "/book.json", produces = "application/json", method = RequestMethod.PUT)
    public Object bookTicket(@RequestBody BookTicketCommand command) {
        commandGateway.send(command);

        RestResponse response = new RestResponse(SUCCESS, null);

        return response;
    }

    @ResponseBody
    @RequestMapping(value = "/free.json", produces = "application/json", method = RequestMethod.GET)
    public Object getFree(@RequestParam(required = false) String filmName,
                                 @RequestParam(required = false) Date filmStartDate,
                                 @RequestParam(required = false) TicketCategory ticketCategory) {
        List<Ticket> tickets = service.getFreeTickets();
        return new RestResponse(SUCCESS, tickets);
    }

    @ResponseBody
    @RequestMapping(value = "/tickets/booked/{" + USER_ID + "}.json", produces = "application/json",
            method = RequestMethod.GET)
    public Object getBookedTicketsJson(@PathVariable(USER_ID) Integer userId) {
        List<Ticket> tickets = service.getFreeTickets();
        return new RestResponse(SUCCESS, tickets);
    }
}
