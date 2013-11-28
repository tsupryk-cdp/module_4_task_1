package com.tsupryk.view.listeners;

import com.tsupryk.api.events.TicketsBookedEvent;
import com.tsupryk.api.events.SeanceTicketsCreatedEvent;
import com.tsupryk.view.entity.Film;
import com.tsupryk.view.entity.Ticket;
import com.tsupryk.api.TicketStatus;
import com.tsupryk.view.service.FilmService;
import com.tsupryk.view.service.TicketService;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import static org.springframework.data.mongodb.core.query.Query.query;
import static org.springframework.data.mongodb.core.query.Criteria.where;

import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.UUID;


/**
 * The Class SeanceEventListener.
 * <p/>
 * Date: 21.11.13
 *
 * @author Vitaliy_Tsupryk
 */
@Component
public class SeanceEventListener {


    @Autowired
    private MongoOperations mongoOperations;

    @EventHandler
    public void handleTicketsCreated(SeanceTicketsCreatedEvent event) {
        Film film = mongoOperations.findById(event.getFilmId(), Film.class, FilmService.COLLECTION_FILMS);

        for (int i = 1; i <= event.getPlaceCount(); i++) {
            Ticket ticket = new Ticket();
            ticket.setId(UUID.randomUUID().toString());
            ticket.setCategory(event.getCategory());
            ticket.setFilmName(film.getTitle());
            ticket.setFilmStartDate(film.getStartTime());
            ticket.setPlaceNumber(i);
            ticket.setStatus(TicketStatus.FREE);

            mongoOperations.save(ticket, TicketService.TICKETS_COLLECTION);
        }
    }

    @EventHandler
    public void handleTicketsBooked(TicketsBookedEvent event) {
        Update update = new Update().set("userId", event.getUserId()).set("status", TicketStatus.BOOKED);

        mongoOperations.updateMulti(query(where("id").in(event.getTicketIds())), update, Ticket.class,
                TicketService.TICKETS_COLLECTION);
    }
}
