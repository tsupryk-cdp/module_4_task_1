package com.tsupryk.web.service;

import com.tsupryk.api.ITicket;
import com.tsupryk.api.RestResponse;
import com.tsupryk.api.ServiceRuntimeException;
import com.tsupryk.api.TicketCategory;
import com.tsupryk.service.api.ITicketService;
import com.tsupryk.web.api.IPdfTicketController;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
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
public class PdfTicketController implements IPdfTicketController {

    private static final String USER_ID = "userId";
    private static final String SUCCESS = "SUCCESS";
    private static final String FAIL = "FAIL";
    private static final String DATASOURCE = "datasource";

    @Autowired
    private ITicketService ticketService;

    @Override
    @RequestMapping(value = "/tickets.pdf", method = RequestMethod.GET)
    public ModelAndView getAvailableTickets(@RequestParam(required = false) String filmName,
                                            @RequestParam(required = false) Date filmStartDate,
                                            @RequestParam(required = false) TicketCategory ticketCategory) {
        List<ITicket> availableTickets = ticketService.getAvailableTickets(filmName, filmStartDate, ticketCategory);
        ModelAndView modelAndView = createModelAndView(availableTickets, "availableTicketsPdfReport");
        return modelAndView;
    }

    /**
     * Books tickets for user.
     *
     * @param userId the user id, required
     * @param ticketList list of tickets to book, field Id in every ticket is required
     * @return the object
     */
//    @Override
    // TODO to remove
    @ResponseBody
    @RequestMapping(value = "/book.pdf", method = RequestMethod.POST)
    public ModelAndView bookTickets(@RequestParam String userId, @RequestBody ITicket[] ticketList) {
        RestResponse response = null;
        try {
            boolean result = ticketService.bookTickets(userId, Arrays.asList(ticketList));
            if (result) {
                response = new RestResponse(SUCCESS, null);
            } else {
                response = new RestResponse(FAIL, null);
            }
        } catch (ServiceRuntimeException e) {
            response = new RestResponse(FAIL, e.getCause().getMessage());
        }
        ModelAndView modelAndView = createModelAndView(Arrays.asList(response), "bookingTicketsPdfReport");
        return modelAndView;
    }

    @Override
    @RequestMapping(value = "/tickets/booked/{" + USER_ID + "}.pdf", method = RequestMethod.GET)
    public ModelAndView getBookedTickets(@PathVariable(USER_ID) String userId,
                                         @RequestParam(required = false) String filmName,
                                         @RequestParam(required = false) Date filmStartDate,
                                         @RequestParam(required = false) TicketCategory ticketCategory) {
        List<ITicket> tickets = ticketService.getBookedTickets(userId, filmName, filmStartDate, ticketCategory);
        ModelAndView modelAndView = createModelAndView(tickets, "bookedTicketsPdfReport");
        return modelAndView;
    }

    private ModelAndView createModelAndView(List<?> availableTickets, String viewName) {
        JRDataSource JRdataSource = new JRBeanCollectionDataSource(availableTickets);
        Map<String,Object> parameterMap = new HashMap<>();
        parameterMap.put(DATASOURCE, JRdataSource);
        return new ModelAndView(viewName, parameterMap);
    }

}
