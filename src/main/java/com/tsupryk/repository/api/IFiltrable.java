package com.tsupryk.repository.api;

import com.tsupryk.api.TicketCategory;
import com.tsupryk.api.TicketStatus;

import java.util.Date;

/**
 * The interface IFiltrable.
 * <p/>
 * User: Vitaliy
 * Date: 06.10.13
 */
public interface IFiltrable {

    public String getFilmName();

    public void setFilmName(String filmName);

    public Date getFilmStartDate();

    public void setFilmStartDate(Date filmStartDate);

    public TicketCategory getCategory();

    public void setCategory(TicketCategory category);

    public Integer getUserId();

    public void setUserId(Integer userId);

    TicketStatus getTicketStatus();

    void setTicketStatus(TicketStatus ticketStatus);
}
