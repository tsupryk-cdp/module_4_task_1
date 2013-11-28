package com.tsupryk.api.events;

import com.tsupryk.api.TicketCategory;

/**
 * The class SeanceTicketsCreatedEvent.
 * <p/>
 * Date: 23.11.13
 * <p/>
 * Author: Vitaliy
 */
public class SeanceTicketsCreatedEvent {

    private String seanceId;

    private String filmId;

    private TicketCategory category;

    private Integer placeCount;

    public SeanceTicketsCreatedEvent() {

    }

    public SeanceTicketsCreatedEvent(String seanceId, String filmId, TicketCategory category, Integer placeCount) {
        this.seanceId = seanceId;
        this.filmId = filmId;
        this.category = category;
        this.placeCount = placeCount;
    }

    public String getSeanceId() {
        return seanceId;
    }

    public void setSeanceId(String seanceId) {
        this.seanceId = seanceId;
    }

    public TicketCategory getCategory() {
        return category;
    }

    public void setCategory(TicketCategory category) {
        this.category = category;
    }

    public Integer getPlaceCount() {
        return placeCount;
    }

    public void setPlaceCount(Integer placeCount) {
        this.placeCount = placeCount;
    }

    public String getFilmId() {
        return filmId;
    }

    public void setFilmId(String filmId) {
        this.filmId = filmId;
    }
}
