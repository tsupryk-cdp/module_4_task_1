package com.tsupryk.view.service;

import com.tsupryk.api.TicketStatus;
import com.tsupryk.view.entity.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

/**
 * The class TicketService.
 * <p/>
 * Date: 25.11.13
 * <p/>
 * Author: Vitaliy
 */
@Service
public class TicketService {

    public static final String TICKETS_COLLECTION = "tickets";

    @Autowired
    MongoOperations repository;

    public List<Ticket> getFreeTickets() {
        return repository.find(query(where("status").is(TicketStatus.FREE)), Ticket.class, TICKETS_COLLECTION);
    }

    public List<Ticket> getBookedTickets(String userId) {
        Criteria criteria = new Criteria().andOperator(where("status").is(TicketStatus.BOOKED), where("userId").is(userId));
        return repository.find(query(criteria), Ticket.class, TICKETS_COLLECTION);
    }

}
