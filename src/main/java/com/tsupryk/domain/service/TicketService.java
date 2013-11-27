package com.tsupryk.domain.service;

import com.tsupryk.api.TicketStatus;
import com.tsupryk.domain.entity.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
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
//        Criteria criteria = new Criteria().andOperator(where("status").is(TicketStatus.FREE), );
        return repository.find(query(where("status").is(TicketStatus.FREE)), Ticket.class, TICKETS_COLLECTION);
    }

    public List<Ticket> getBookedTickets(Integer userId) {
        return repository.find(query(where("status").is(TicketStatus.BOOKED).and("userId").is(userId)), Ticket.class);
    }

    public Ticket getById(String id) {
        return repository.findById(id, Ticket.class, TICKETS_COLLECTION);
    }

}
