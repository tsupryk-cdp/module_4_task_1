package com.tsupryk.axon.commands;


/**
 * The class BookTicketCommand.
 * <p/>
 * Date: 21.11.13
 * <p/>
 * Author: Vitaliy
 */
public class BookTicketCommand {

    private Integer userId;

    private Integer ticketId;

    public BookTicketCommand() {

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
