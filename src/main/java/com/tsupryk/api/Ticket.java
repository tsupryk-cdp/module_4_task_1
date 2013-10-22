package com.tsupryk.api;

import javax.persistence.*;
import java.util.Date;

/**
 * The class Ticket.
 * <p/>
 * User: Vitaliy
 * Date: 06.10.13
 */
@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String filmName;

    private Date filmStartDate;

    private TicketCategory category;

    private Integer placeNumber;

    private TicketStatus status;

    @ManyToOne
    private User user;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
