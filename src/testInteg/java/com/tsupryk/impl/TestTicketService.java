package com.tsupryk.impl;

import com.tsupryk.api.entity.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * The class TestTicketService.
 * <p/>
 * User: Vitaliy
 * Date: 16.10.13
 */
@Service
public class TestTicketService {

    @Autowired
    TestTicketRepository repository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void insertRequiresNew(boolean withServiceException, boolean withRepositoryException) {
        repository.insertNotSafe(getTicket(10, "Service before"));

        try {
            repository.insertRequiresNew(getTicket(20, "Repository"), withRepositoryException);
        } catch (RuntimeException ex) { }

        repository.insertNotSafe(getTicket(11, "Service after"));
        if (withServiceException) {
            throw new RuntimeException("Service exception");
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void insertRequired(boolean withServiceException, boolean withRepositoryException) {
        repository.insertNotSafe(getTicket(10, "Service before"));

        try {
            repository.insertRequired(getTicket(20, "Repository"), withRepositoryException);
        } catch (RuntimeException ex) { }

        repository.insertNotSafe(getTicket(11, "Service after"));
        if (withServiceException) {
            throw new RuntimeException("Service exception");
        }
    }

    private Ticket getTicket(int id, String filmName) {
        Ticket t = new Ticket();
        t.setId(id);
        t.setFilmName(filmName);
        return t;
    }

}
