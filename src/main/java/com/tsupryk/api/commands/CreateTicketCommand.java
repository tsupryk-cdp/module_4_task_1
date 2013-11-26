package com.tsupryk.api.commands;

import com.tsupryk.api.entity.TicketCategory;
import com.tsupryk.api.entity.TicketStatus;
import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

import java.util.Date;

/**
 * The class CreateTicketCommand.
 * <p/>
 * Date: 17.11.13
 * <p/>
 * Author: Vitaliy
 */
public class CreateTicketCommand {

    @TargetAggregateIdentifier
    private String ticketId;

    private String filmId;

    private TicketCategory category;

    private Integer placeNumber;

    private TicketStatus status;


    public CreateTicketCommand() {

    }

    public CreateTicketCommand(String ticketId, String filmId, TicketCategory category, Integer placeNumber,
                               TicketStatus status) {
        this.ticketId = ticketId;
        this.filmId = filmId;
        this.category = category;
        this.placeNumber = placeNumber;
        this.status = status;
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public String getFilmId() {
        return filmId;
    }

    public void setFilmId(String filmId) {
        this.filmId = filmId;
    }

    public TicketCategory getCategory() {
        return category;
    }

    public void setCategory(TicketCategory category) {
        this.category = category;
    }

    public Integer getPlaceNumber() {
        return placeNumber;
    }

    public void setPlaceNumber(Integer placeNumber) {
        this.placeNumber = placeNumber;
    }

    public TicketStatus getStatus() {
        return status;
    }

    public void setStatus(TicketStatus status) {
        this.status = status;
    }

}
