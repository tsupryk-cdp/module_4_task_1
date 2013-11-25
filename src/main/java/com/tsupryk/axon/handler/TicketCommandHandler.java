package com.tsupryk.axon.handler;

import com.tsupryk.axon.aggregates.TicketAR;
import com.tsupryk.axon.commands.BookTicketCommand;
import com.tsupryk.axon.commands.CreateTicketCommand;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * The class UserCommandHandler.
 * <p/>
 * Date: 17.11.13
 * <p/>
 * Author: Vitaliy
 */
@Component
public class TicketCommandHandler {

    @Autowired
    private EventSourcingRepository<TicketAR> repository;


    @CommandHandler
    public void handleCreateTicket(CreateTicketCommand command) {
        TicketAR ticket = new TicketAR(command);
        repository.add(ticket);
    }

    @CommandHandler
    public void handleBookTicket(BookTicketCommand command) {
        TicketAR ticket = repository.load(command.getTicketId());
        ticket.book(command);
    }
}
