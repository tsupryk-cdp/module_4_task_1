package com.tsupryk.api.commands;

import com.tsupryk.api.entity.TicketCategory;

/**
 * The class GenerateFilmTicketsCommand.
 * <p/>
 * Date: 26.11.13
 * <p/>
 * Author: Vitaliy
 */
public class GenerateFilmTicketsCommand {

    private String filmId;

    private Integer placeCount;

    private TicketCategory ticketsCategory;

    public String getFilmId() {
        return filmId;
    }

    public void setFilmId(String filmId) {
        this.filmId = filmId;
    }

    public Integer getPlaceCount() {
        return placeCount;
    }

    public void setPlaceCount(Integer placeCount) {
        this.placeCount = placeCount;
    }

    public TicketCategory getTicketsCategory() {
        return ticketsCategory;
    }

    public void setTicketsCategory(TicketCategory ticketsCategory) {
        this.ticketsCategory = ticketsCategory;
    }
}
