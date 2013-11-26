package com.tsupryk.api.commands;


import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

/**
 * The class BookTicketCommand.
 * <p/>
 * Date: 21.11.13
 * <p/>
 * Author: Vitaliy
 */
public class BookTicketCommand {

    @TargetAggregateIdentifier
    private String ticketId;

    private String userId;

    public BookTicketCommand() {

    }

    public BookTicketCommand(String ticketId, String userId) {
        this.ticketId = ticketId;
        this.userId = userId;
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
