package com.tsupryk.repository;

import com.tsupryk.api.entity.TicketCategory;
import com.tsupryk.api.entity.TicketStatus;
import com.tsupryk.api.IFiltrable;

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
    private TicketStatus status;

    private Integer userId;

    public TicketFilter(String filmName, Date filmStartDate, TicketCategory category, TicketStatus status,
                        Integer userId) {
        this.filmName = filmName;
        this.filmStartDate = filmStartDate;
        this.category = category;
        this.status = status;
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
    public Integer getUserId() {
        return userId;
    }

    @Override
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public TicketStatus getStatus() {
        return status;
    }

    @Override
    public void setStatus(TicketStatus status) {
        this.status = status;
    }
}
