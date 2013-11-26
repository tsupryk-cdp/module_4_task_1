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

    private String userId;

    private List<String> ticketIds;

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
}
