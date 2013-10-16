package com.tsupryk.api;

import java.util.Date;

/**
 * The class Ticket.
 * <p/>
 * User: Vitaliy
 * Date: 06.10.13
 */
public class Ticket {

    private Integer id;
    private String filmName;
    private Date filmStartDate;
    private TicketCategory category;
    private Integer placeNumber;

    private TicketStatus status;
    private Integer userId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
