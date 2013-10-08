package com.tsupryk.web.service;

import com.tsupryk.api.ITicket;
import com.tsupryk.api.RestResponse;
import com.tsupryk.api.ServiceRuntimeException;
import com.tsupryk.api.TicketCategory;
import com.tsupryk.service.api.ITicketService;
import com.tsupryk.web.api.ITicketController;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
public class TicketController implements ITicketController {

    private static final String USER_ID = "userId";
    private static final String SUCCESS = "SUCCESS";
    private static final String FAIL = "FAIL";

    @Autowired
    private ITicketService ticketService;

    @Override
    @ResponseBody
    @RequestMapping(value = "/tickets", method = RequestMethod.GET)
    public Object getAvailableTickets(ModelAndView modelAndView, @RequestParam(required = false) String filmName,
                                      @RequestParam(required = false) Date filmStartDate,
                                      @RequestParam(required = false) TicketCategory ticketCategory) {

        List<ITicket> availableTickets = null;
        RestResponse response = null;
        try {
            availableTickets = ticketService.getAvailableTickets(filmName, filmStartDate, ticketCategory);
            response = new RestResponse(SUCCESS, availableTickets);
        } catch (ServiceRuntimeException e) {
            response = new RestResponse(FAIL, e.getCause().getMessage());
        }

        Map<String,Object> parameterMap = new HashMap<String,Object>();
        JRDataSource JRdataSource = new JRBeanCollectionDataSource(availableTickets);

        parameterMap.put("datasource", JRdataSource);

        modelAndView = new ModelAndView("availableTicketsPdfReport", parameterMap);

        return modelAndView;

//        return response;
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
    @RequestMapping(value = "/book", produces = "application/json", method = RequestMethod.POST)
    public Object bookTickets(@RequestParam String userId, @RequestBody List<ITicket> ticketList) {
        RestResponse response = null;
        try {
            boolean result = ticketService.bookTickets(userId, ticketList);
            if (result) {
                response = new RestResponse(SUCCESS, null);
            } else {
                response = new RestResponse(FAIL, null);
            }
        } catch (ServiceRuntimeException e) {
            response = new RestResponse(FAIL, e.getCause().getMessage());
        }
        return response;
    }

    @Override
    @ResponseBody
    @RequestMapping(value = "/tickets/booked/{" + USER_ID + "}/", produces = "application/json",
            method = RequestMethod.GET)
    public Object getBookedTickets(@PathVariable(USER_ID) String userId,
                                   @RequestParam(required = false) String filmName,
                                   @RequestParam(required = false) Date filmStartDate,
                                   @RequestParam(required = false) TicketCategory ticketCategory) {
        RestResponse response = null;
        try {
            List<ITicket> tickets = ticketService.getBookedTickets(userId, filmName, filmStartDate, ticketCategory);
            response = new RestResponse(SUCCESS, tickets);
        } catch (ServiceRuntimeException e) {
            response = new RestResponse(FAIL, e.getCause().getMessage());
        }
//        to implement
//        modelAndView = new ModelAndView("bookedTicketsPdfReport", parameterMap);
        return response;
    }

}
