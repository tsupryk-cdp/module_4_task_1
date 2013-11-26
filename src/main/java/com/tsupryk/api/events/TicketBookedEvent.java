package com.tsupryk.api.events;


/**
 * The class TicketBookedEvent.
 * <p/>
 * Date: 24.11.13
 * <p/>
 * Author: Vitaliy
 */
public class TicketBookedEvent {

    private String userId;

    private String ticketId;

    public TicketBookedEvent() {

    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }
}
