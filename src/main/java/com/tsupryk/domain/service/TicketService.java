package com.tsupryk.domain.service;

import com.tsupryk.domain.entity.Ticket;
import com.tsupryk.domain.entity.TicketCategory;
import com.tsupryk.api.TicketStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import static org.springframework.data.mongodb.core.query.Query.query;
import static org.springframework.data.mongodb.core.query.Criteria.where;

import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
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

    public void init() {
        int count = 0;
        for (int j = 0; j < 2; j++) {
            for (int i = 1; i <= 10; i++) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(new Date());
                calendar.add(Calendar.DAY_OF_MONTH, 5 + j);

                Ticket ticket = new Ticket();
                ticket.setId(count++);
                ticket.setStatus(TicketStatus.FREE);
                ticket.setCategory(TicketCategory.STANDARD);
                if (j > 0) {
                    ticket.setFilmName("Terminator");
                } else {
                    ticket.setFilmName("Independence Day");
                }
                ticket.setFilmStartDate(calendar.getTime());
                ticket.setPlaceNumber(i);
                repository.insert(ticket, TICKETS_COLLECTION);
            }
        }
    }

}
