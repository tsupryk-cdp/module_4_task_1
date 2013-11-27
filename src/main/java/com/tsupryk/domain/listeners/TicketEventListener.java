package com.tsupryk.domain.listeners;

import com.tsupryk.api.events.TicketsBookedEvent;
import com.tsupryk.api.events.SeanceTicketsCreatedEvent;
import com.tsupryk.domain.entity.Film;
import com.tsupryk.domain.entity.Ticket;
import com.tsupryk.api.TicketStatus;
import com.tsupryk.domain.service.FilmService;
import com.tsupryk.domain.service.TicketService;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import static org.springframework.data.mongodb.core.query.Query.query;
import static org.springframework.data.mongodb.core.query.Criteria.where;

import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;


/**
 * The Class UserEventListener.
 * <p/>
 * Date: 21.11.13
 *
 * @author Vitaliy_Tsupryk
 */
@Component
public class TicketEventListener {


    @Autowired
    private MongoOperations mongoOperations;

    @EventHandler
    public void handleTicketsCreated(SeanceTicketsCreatedEvent event) {
        Film film = mongoOperations.findById(event.getFilmId(), Film.class, FilmService.COLLECTION_FILMS);

        Ticket ticket = new Ticket();
        ticket.setCategory(event.getCategory());
        ticket.setFilmName(film.getTitle());
        ticket.setFilmStartDate(film.getStartTime());
        ticket.setPlaceNumber(event.getPlaceNumber());
        ticket.setStatus(TicketStatus.FREE);

        mongoOperations.save(ticket, TicketService.TICKETS_COLLECTION);
    }

    @EventHandler
    public void handleTicketsBooked(TicketsBookedEvent event) {
        mongoOperations.updateMulti(query(where("id").in(event.getTicketIds())),
                new Update().set("userId", event.getUserId()).set("status", TicketStatus.BOOKED), TicketService.TICKETS_COLLECTION);
    }
}
