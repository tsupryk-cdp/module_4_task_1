package com.tsupryk.axon.service;

import com.tsupryk.api.entity.Ticket;
import com.tsupryk.api.entity.TicketStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import static org.springframework.data.mongodb.core.query.Query.query;
import static org.springframework.data.mongodb.core.query.Criteria.where;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The class TicketService.
 * <p/>
 * Date: 25.11.13
 * <p/>
 * Author: Vitaliy
 */
@Service
public class TicketService {

    @Autowired
    MongoOperations repository;

    public List<Ticket> getFreeTickets() {
//        Criteria criteria = new Criteria().andOperator(where("status").is(TicketStatus.FREE), );
        return repository.find(query(where("status").is(TicketStatus.FREE)), Ticket.class, "tickets");
    }

    public List<Ticket> getBookedTickets(Integer userId) {
        return repository.find(query(where("status").is(TicketStatus.BOOKED).and("userId").is(userId)), Ticket.class);
    }

}
