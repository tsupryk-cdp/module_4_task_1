package com.tsupryk.service.util;

import com.tsupryk.api.TicketCategory;
import com.tsupryk.api.TicketType;
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
        return new TicketFilter(filmName, filmStartDate, ticketCategory, TicketType.FREE, null);
    }

    public static IFiltrable buildBookedTicketsFilter(String userId, String filmName, Date filmStartDate, TicketCategory ticketCategory){
        return new TicketFilter(filmName, filmStartDate, ticketCategory, TicketType.BOOKED, userId);
    }

}
