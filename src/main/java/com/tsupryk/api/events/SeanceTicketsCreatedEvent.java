package com.tsupryk.api.events;

import com.tsupryk.domain.entity.TicketCategory;

import java.util.List;

/**
 * The class SeanceTicketsCreatedEvent.
 * <p/>
 * Date: 23.11.13
 * <p/>
 * Author: Vitaliy
 */
public class SeanceTicketsCreatedEvent {

    private String seanceId;

    private List<String> ticketIds;

    private String filmId;

    private TicketCategory category;

    private Integer placeNumber;

    public SeanceTicketsCreatedEvent() {

    }

    public SeanceTicketsCreatedEvent(String seanceId, List<String> ticketIds, String filmId, TicketCategory category, Integer placeNumber) {
        this.seanceId = seanceId;
        this.ticketIds = ticketIds;
        this.filmId = filmId;
        this.category = category;
        this.placeNumber = placeNumber;
    }

    public String getSeanceId() {
        return seanceId;
    }

    public void setSeanceId(String seanceId) {
        this.seanceId = seanceId;
    }

    public List<String> getTicketIds() {
        return ticketIds;
    }

    public void setTicketIds(List<String> ticketIds) {
        this.ticketIds = ticketIds;
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
