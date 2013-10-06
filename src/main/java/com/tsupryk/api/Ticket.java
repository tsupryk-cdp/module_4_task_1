package com.tsupryk.api;

import java.util.Date;

/**
 * The class Ticket.
 * <p/>
 * User: Vitaliy
 * Date: 06.10.13
 */
public class Ticket implements ITicket {

    private String id;
    private String filmName;
    private Date filmStartDate;
    private TicketCategory category;
    private Integer placeNumber;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getFilmName() {
        return filmName;
    }

    @Override
    public void setFilmName(String filmName) {
        this.filmName = filmName;
    }

    @Override
    public Date getFilmStartDate() {
        return filmStartDate;
    }

    @Override
    public void setFilmStartDate(Date filmStartDate) {
        this.filmStartDate = filmStartDate;
    }

    @Override
    public TicketCategory getCategory() {
        return category;
    }

    @Override
    public void setCategory(TicketCategory category) {
        this.category = category;
    }

    @Override
    public Integer getPlaceNumber() {
        return placeNumber;
    }

    @Override
    public void setPlaceNumber(Integer placeNumber) {
        this.placeNumber = placeNumber;
    }
}
