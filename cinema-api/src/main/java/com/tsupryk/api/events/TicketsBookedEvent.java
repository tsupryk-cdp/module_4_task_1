package com.tsupryk.api.events;


import java.util.List;

/**
 * The class TicketsBookedEvent.
 * <p/>
 * Date: 24.11.13
 * <p/>
 * Author: Vitaliy
 */
public class TicketsBookedEvent {

    private String seanceId;

    private String userId;

    private List<String> ticketIds;

    public TicketsBookedEvent() {

    }

    public TicketsBookedEvent(String seanceId, String userId, List<String> ticketIds) {
        this.seanceId = seanceId;
        this.userId = userId;
        this.ticketIds = ticketIds;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<String> getTicketIds() {
        return ticketIds;
    }

    public void setTicketIds(List<String> ticketIds) {
        this.ticketIds = ticketIds;
    }

    public String getSeanceId() {
        return seanceId;
    }

    public void setSeanceId(String seanceId) {
        this.seanceId = seanceId;
    }
}
