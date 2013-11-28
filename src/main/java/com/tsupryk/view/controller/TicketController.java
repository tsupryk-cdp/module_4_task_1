package com.tsupryk.view.controller;

import com.tsupryk.view.RestResponse;
import com.tsupryk.api.commands.*;
import com.tsupryk.view.entity.Ticket;
import com.tsupryk.api.TicketCategory;
import com.tsupryk.view.service.TicketService;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
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
    @RequestMapping(value = "/createFilm.json", produces = "application/json", method = RequestMethod.POST)
    public Object createFilm(@RequestBody CreateFilmCommand command) {
        commandGateway.send(command);
        RestResponse response = new RestResponse(SUCCESS, null);
        return response;
    }

    @ResponseBody
    @RequestMapping(value = "/crateTickets.json", produces = "application/json", method = RequestMethod.POST)
    public Object generateFilmTickets(@RequestBody CreateSeanceTicketsCommand command) {
        commandGateway.send(command);
        RestResponse response = new RestResponse(SUCCESS, null);
        return response;
    }

    @ResponseBody
    @RequestMapping(value = "/createUser.json", produces = "application/json", method = RequestMethod.POST)
    public Object createUser(@RequestBody CreateUserCommand command) {
        commandGateway.send(command);
        RestResponse response = new RestResponse(SUCCESS, null);
        return response;
    }

    @ResponseBody
    @RequestMapping(value = "/bookUserTickets.json", produces = "application/json", method = RequestMethod.PUT)
    public Object bookUserTickets(@RequestBody BookUserTicketsCommand command) {
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
