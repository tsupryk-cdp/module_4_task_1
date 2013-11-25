package com.tsupryk.axon.events;


/**
 * The class TicketBookedEvent.
 * <p/>
 * Date: 24.11.13
 * <p/>
 * Author: Vitaliy
 */
public class TicketBookedEvent {

    private Integer userId;

    private Integer ticketId;

    public TicketBookedEvent() {

    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getTicketId() {
        return ticketId;
    }

    public void setTicketId(Integer ticketId) {
        this.ticketId = ticketId;
    }
}
