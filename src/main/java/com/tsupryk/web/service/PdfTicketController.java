package com.tsupryk.web.service;

import com.tsupryk.api.*;
import com.tsupryk.service.api.ITicketService;
import com.tsupryk.service.util.TicketUtil;
import com.tsupryk.web.api.IPdfTicketController;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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

    private static final String EMPTY_FILTER_FIELDS = "You should sprecify at least one parameter: filmName, filmStartDate or ticketCategory";

    @Autowired
    private ITicketService ticketService;

    @Override
    @RequestMapping(value = "/tickets.pdf", method = RequestMethod.GET)
    public ModelAndView getAvailableTickets(@RequestParam(required = false) String filmName,
                                            @RequestParam(required = false) Date filmStartDate,
                                            @RequestParam(required = false) TicketCategory ticketCategory) {
        if (TicketUtil.isEmpty(filmName) && TicketUtil.isEmpty(filmStartDate) && TicketUtil.isEmpty(ticketCategory)) {
            throw new ServiceRuntimeException(EMPTY_FILTER_FIELDS);
        }
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
    @Override
    @ResponseBody
    @RequestMapping(value = "/book.pdf", method = RequestMethod.POST)
    public ModelAndView bookTickets(@RequestParam Integer userId, @RequestBody List<Ticket> ticketList) {
        TicketUtil.validateTickets(ticketList);
        RestResponse response = null;
        boolean result = ticketService.bookTickets(userId, ticketList);
        if (result) {
            response = new RestResponse(SUCCESS, null);
        } else {
            response = new RestResponse(FAIL, null);
        }
        ModelAndView modelAndView = createModelAndView(Arrays.asList(response), "infoPdfReport");
        return modelAndView;
    }

    @Override
    @RequestMapping(value = "/tickets/booked/{" + USER_ID + "}.pdf", method = RequestMethod.GET)
    public ModelAndView getBookedTickets(@PathVariable(USER_ID) Integer userId,
                                         @RequestParam(required = false) String filmName,
                                         @RequestParam(required = false) Date filmStartDate,
                                         @RequestParam(required = false) TicketCategory ticketCategory) {
        if (TicketUtil.isEmpty(userId) && TicketUtil.isEmpty(filmName) && TicketUtil.isEmpty(filmStartDate)
                && TicketUtil.isEmpty(ticketCategory)) {
            throw new ServiceRuntimeException(EMPTY_FILTER_FIELDS);
        }
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

    @ExceptionHandler(ServiceRuntimeException.class)
    public ModelAndView handleCustomException(ServiceRuntimeException ex) {
        ModelAndView mav = new ModelAndView("error");
        mav.addObject("exception", ex);
        return mav;
    }

}
