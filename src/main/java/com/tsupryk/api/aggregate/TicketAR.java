package com.tsupryk.api.aggregate;

import com.tsupryk.api.commands.CreateTicketCommand;
import com.tsupryk.api.entity.Film;
import com.tsupryk.api.entity.TicketCategory;
import com.tsupryk.api.entity.TicketStatus;
import com.tsupryk.api.commands.BookTicketCommand;
import com.tsupryk.api.events.TicketBookedEvent;
import com.tsupryk.api.events.TicketCreatedEvent;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;

/**
 * The class TicketAR.
 * <p/>
 * Date: 17.11.13
 * <p/>
 * Author: Vitaliy
 */
public class TicketAR extends AbstractAnnotatedAggregateRoot<String> {

    @AggregateIdentifier
    private String id;

    private String filmId;

    private TicketCategory category;

    private Integer placeNumber;

    private TicketStatus status;

    private String userId;

    public TicketAR() {

    }

    public TicketAR(CreateTicketCommand command) {
        TicketCreatedEvent event = new TicketCreatedEvent(command.getTicketId(), command.getFilmId(),
                command.getCategory(), command.getPlaceNumber());
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
        filmId = event.getFilmId();
        category = event.getCategory();
        placeNumber = event.getPlaceNumber();
        status = TicketStatus.FREE;
    }

    @EventHandler
    public void on(TicketBookedEvent event) {
        id = event.getTicketId();
        userId = event.getUserId();
        status = TicketStatus.BOOKED;
    }

}
