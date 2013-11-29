package com.tsupryk.api.commands;

import java.util.List;

/**
 * The class BookUserTicketsCommand.
 * <p/>
 * Date: 26.11.13
 * <p/>
 * Author: Vitaliy
 */
public class BookUserTicketsCommand {

    private String seanceId;

    private String userId;

    private List<String> ticketIds;

    public BookUserTicketsCommand() {

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
