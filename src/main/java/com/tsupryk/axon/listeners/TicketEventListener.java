package com.tsupryk.axon.listeners;

import com.tsupryk.api.entity.Ticket;
import com.tsupryk.api.entity.TicketStatus;
import com.tsupryk.axon.events.TicketBookedEvent;
import com.tsupryk.axon.events.TicketCreatedEvent;
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

    public static final String COLLECTION_TICKETS = "tickets";

    @Autowired
    private MongoOperations mongoOperations;

    @EventHandler
    public void handleTicketCreated(TicketCreatedEvent event) {
        Ticket ticket = new Ticket(event);
        mongoOperations.save(ticket, COLLECTION_TICKETS);
    }

    @EventHandler
    public void handleTicketBooked(TicketBookedEvent event) {
        mongoOperations.updateFirst(query(where("_id").is(event.getTicketId())),
                new Update().set("userId", event.getUserId()).set("status", TicketStatus.BOOKED), COLLECTION_TICKETS);

    }
}
