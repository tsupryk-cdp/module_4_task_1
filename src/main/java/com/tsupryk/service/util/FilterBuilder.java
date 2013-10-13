package com.tsupryk.service.util;

import com.tsupryk.api.TicketCategory;
import com.tsupryk.api.TicketStatus;
import com.tsupryk.repository.api.IFiltrable;
import com.tsupryk.repository.service.TicketFilter;

import java.util.Date;

/**
 * The class FilterBuilder.
 * <p/>
 * User: Vitaliy
 * Date: 06.10.13
 */
public class FilterBuilder {

    private FilterBuilder(){
    }

    public static IFiltrable buildFreeTicketsFilter(String filmName, Date filmStartDate, TicketCategory ticketCategory){
        return new TicketFilter(filmName, filmStartDate, ticketCategory, TicketStatus.FREE, null);
    }

    public static IFiltrable buildBookedTicketsFilter(Integer userId, String filmName, Date filmStartDate, TicketCategory ticketCategory){
        return new TicketFilter(filmName, filmStartDate, ticketCategory, TicketStatus.BOOKED, userId);
    }

}
