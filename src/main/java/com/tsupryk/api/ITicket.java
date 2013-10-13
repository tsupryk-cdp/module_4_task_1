package com.tsupryk.api;

import java.util.Date;

/**
 * The interface ITicket.
 * <p/>
 * User: Vitaliy
 * Date: 06.10.13
 */
public interface ITicket {

    public Integer getId();

    public void setId(Integer id);

    public String getFilmName();

    public void setFilmName(String filmName);

    public Date getFilmStartDate();

    public void setFilmStartDate(Date filmStartDate);

    public TicketCategory getCategory();

    public void setCategory(TicketCategory category);

    public Integer getPlaceNumber();

    public void setPlaceNumber(Integer placeNumber);

    TicketStatus getStatus();

    void setStatus(TicketStatus state);

    Integer getUserId();

    void setUserId(Integer userId);
}
