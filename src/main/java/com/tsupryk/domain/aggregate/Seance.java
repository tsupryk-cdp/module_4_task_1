package com.tsupryk.domain.aggregate;

import com.tsupryk.api.commands.BookUserTicketsCommand;
import com.tsupryk.api.commands.CreateSeanceTicketsCommand;
import com.tsupryk.api.events.SeanceTicketsCreatedEvent;
import com.tsupryk.api.events.TicketsBookedEvent;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The class Seance.
 * <p/>
 * Date: 17.11.13
 * <p/>
 * Author: Vitaliy
 */
public class Seance extends AbstractAnnotatedAggregateRoot<String> {

    @AggregateIdentifier
    private String id;

    private String filmId;

    private List<String> ticketIds;

    private Map<String, List<String>> userIds = new HashMap<>();

    private Integer placeCount;

    public Seance() {

    }

    public Seance(CreateSeanceTicketsCommand command) {
//        id = command.getSeanceId();
//        ticketIds = command.getTicketIds();
//        placeCount = command.getPlaceCount();
//        filmId = command.getFilmId();

        SeanceTicketsCreatedEvent event = new SeanceTicketsCreatedEvent(command.getSeanceId(), command.getTicketIds(),
                command.getFilmId(), command.getTicketsCategory(), command.getPlaceCount());
        apply(event);
    }

    public void book(BookUserTicketsCommand command) {
//        userIds.put(command.getUserId(), command.getTicketIds());

        TicketsBookedEvent event = new TicketsBookedEvent(id, command.getUserId(), command.getTicketIds());
        apply(event);
    }

    @EventHandler
    public void on(SeanceTicketsCreatedEvent event) {
        id = event.getSeanceId();
        filmId = event.getFilmId();
        ticketIds.addAll(event.getTicketIds());
        placeCount = event.getPlaceNumber();
    }

    @EventHandler
    public void on(TicketsBookedEvent event) {
        userIds.put(event.getUserId(), event.getTicketIds());
    }

}
