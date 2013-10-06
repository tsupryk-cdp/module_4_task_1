package com.tsupryk.repository.service;

import com.tsupryk.api.TicketCategory;
import com.tsupryk.api.TicketType;
import com.tsupryk.repository.api.IFiltrable;

import java.util.Date;

/**
 * The class TicketFilter.
 * <p/>
 * User: Vitaliy
 * Date: 06.10.13
 */
public class TicketFilter implements IFiltrable {

    private String filmName;
    private Date filmStartDate;
    private TicketCategory category;
    private TicketType ticketType;

    private String userId;

    public TicketFilter(String filmName, Date filmStartDate, TicketCategory category, TicketType ticketType,
                        String userId) {
        this.filmName = filmName;
        this.filmStartDate = filmStartDate;
        this.category = category;
        this.ticketType = ticketType;
        this.userId = userId;
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
    public String getUserId() {
        return userId;
    }

    @Override
    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public TicketType getTicketType() {
        return ticketType;
    }

    @Override
    public void setTicketType(TicketType ticketType) {
        this.ticketType = ticketType;
    }
}
