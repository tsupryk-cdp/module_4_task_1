package com.tsupryk.service.util;

import com.tsupryk.api.ServiceRuntimeException;
import com.tsupryk.api.Ticket;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * The class TicketUtil.
 * <p/>
 * User: Vitaliy
 * Date: 09.10.13
 */
public class TicketUtil {

    public static boolean isEmpty(Object value) {
        return value instanceof String ? StringUtils.isEmpty(value) : value == null;
    }

    public static void validateTickets(List<Ticket> ticketList) {
        for (Ticket ticket : ticketList) {
            checkField(ticket.getId(), "Wrong or empty field Id.");
            checkField(ticket.getCategory(), "Wrong or empty field Category.");
        }
    }

    public static void checkField(Object value, String message) {
        if (value instanceof String && StringUtils.isEmpty(value) || value == null) {
            throw new ServiceRuntimeException(message);
        }
    }
}
