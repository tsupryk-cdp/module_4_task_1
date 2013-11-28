package com.tsupryk.view.entity;

import com.tsupryk.api.TicketCategory;
import com.tsupryk.api.TicketStatus;
import org.springframework.data.annotation.Id;

import java.util.Date;

/**
 * The class Ticket.
 * <p/>
 * User: Vitaliy
 * Date: 06.10.13
 */
public class Ticket {

    @Id
    private String id;

    private String filmName;

    private Date filmStartDate;

    private TicketCategory category;

    private Integer placeNumber;

    private TicketStatus status;

    private String userId;

    public Ticket() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public String getUserId() {
        return userId;
    }
}
