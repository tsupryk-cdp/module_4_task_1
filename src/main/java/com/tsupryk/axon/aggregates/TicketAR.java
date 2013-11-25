package com.tsupryk.axon.aggregates;

import com.tsupryk.api.entity.TicketCategory;
import com.tsupryk.api.entity.TicketStatus;
import com.tsupryk.axon.commands.BookTicketCommand;
import com.tsupryk.axon.commands.CreateTicketCommand;
import com.tsupryk.axon.events.TicketBookedEvent;
import com.tsupryk.axon.events.TicketCreatedEvent;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;

import java.util.Date;

/**
 * The class TicketAR.
 * <p/>
 * Date: 17.11.13
 * <p/>
 * Author: Vitaliy
 */
public class TicketAR extends AbstractAnnotatedAggregateRoot {

    @AggregateIdentifier
    private Integer id;

    private String filmName;

    private Date filmStartDate;

    private TicketCategory category;

    private Integer placeNumber;

    private TicketStatus status;

    private Integer userId;

    public TicketAR() {

    }

    public TicketAR(CreateTicketCommand command) {
        id = command.getTicketId();
        filmName = command.getFilmName();
        filmStartDate = command.getFilmStartDate();
        category = command.getCategory();
        placeNumber = command.getPlaceNumber();
        status = command.getStatus();
        TicketCreatedEvent event = new TicketCreatedEvent(command);
        apply(event);
    }

    public void book(BookTicketCommand command) {
        id = command.getTicketId();
        userId = command.getUserId();
        TicketBookedEvent event = new TicketBookedEvent();
        event.setTicketId(id);
        event.setUserId(userId);
        apply(event);
    }

    @EventHandler
    public void on(TicketCreatedEvent event) {
        id = event.getTicketId();
        filmName = event.getFilmName();
        filmStartDate = event.getFilmStartDate();
        category = event.getCategory();
        placeNumber = event.getPlaceNumber();
        status = event.getStatus();
    }

    @EventHandler
    public void on(TicketBookedEvent event) {
        id = event.getTicketId();
        userId = event.getUserId();
    }

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
