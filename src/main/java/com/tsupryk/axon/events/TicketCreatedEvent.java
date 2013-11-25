package com.tsupryk.axon.events;

import com.tsupryk.api.entity.TicketCategory;
import com.tsupryk.api.entity.TicketStatus;
import com.tsupryk.axon.commands.CreateTicketCommand;

import java.util.Date;

/**
 * The class TicketCreatedEvent.
 * <p/>
 * Date: 23.11.13
 * <p/>
 * Author: Vitaliy
 */
public class TicketCreatedEvent {

    private Integer ticketId;

    private String filmName;

    private Date filmStartDate;

    private TicketCategory category;

    private Integer placeNumber;

    private TicketStatus status;

    public TicketCreatedEvent() {

    }

    public TicketCreatedEvent(CreateTicketCommand command) {
        ticketId = command.getTicketId();
        filmName = command.getFilmName();
        filmStartDate = command.getFilmStartDate();
        category = command.getCategory();
        placeNumber = command.getPlaceNumber();
        status = command.getStatus();
    }

    public Integer getTicketId() {
        return ticketId;
    }

    public void setTicketId(Integer ticketId) {
        this.ticketId = ticketId;
    }

    public String getFilmName() {
        return filmName;
    }

    public void setFilmName(String filmName) {
        this.filmName = filmName;
    }

    public Date getFilmStartDate() {
        return filmStartDate;
    }

    public void setFilmStartDate(Date filmStartDate) {
        this.filmStartDate = filmStartDate;
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
