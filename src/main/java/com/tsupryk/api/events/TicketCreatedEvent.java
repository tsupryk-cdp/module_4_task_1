package com.tsupryk.api.events;

import com.tsupryk.domain.entity.TicketCategory;

/**
 * The class TicketCreatedEvent.
 * <p/>
 * Date: 23.11.13
 * <p/>
 * Author: Vitaliy
 */
public class TicketCreatedEvent {

    private String ticketId;

    private String filmId;

    private TicketCategory category;

    private Integer placeNumber;

    public TicketCreatedEvent() {

    }

    public TicketCreatedEvent(String ticketId, String filmId, TicketCategory category, Integer placeNumber) {
        this.ticketId = ticketId;
        this.filmId = filmId;
        this.category = category;
        this.placeNumber = placeNumber;
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
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

    public String getFilmId() {
        return filmId;
    }

    public void setFilmId(String filmId) {
        this.filmId = filmId;
    }
}
