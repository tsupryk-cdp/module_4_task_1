package com.tsupryk.axon.handler;

import com.tsupryk.api.ServiceRuntimeException;
import com.tsupryk.api.aggregate.TicketAR;
import com.tsupryk.api.aggregate.UserAR;
import com.tsupryk.api.commands.BookTicketCommand;
import com.tsupryk.api.commands.BookUserTicketsCommand;
import com.tsupryk.api.commands.CreateTicketCommand;
import com.tsupryk.api.commands.GenerateFilmTicketsCommand;
import com.tsupryk.api.entity.Film;
import com.tsupryk.api.entity.TicketStatus;
import com.tsupryk.axon.service.FilmService;
import com.tsupryk.axon.service.TicketService;
import com.tsupryk.axon.service.UserService;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * The class UserCommandHandler.
 * <p/>
 * Date: 17.11.13
 * <p/>
 * Author: Vitaliy
 */
@Component
public class TicketCommandHandler {

    @Resource(name = "ticketES")
    private EventSourcingRepository<TicketAR> repository;

    @Autowired
    private CommandGateway commandGateway;

    @Autowired
    private TicketService ticketService;

    @Autowired
    private UserService userService;

    @Autowired
    FilmService filmService;

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

    @CommandHandler
    public void handleBookUserTickets(BookUserTicketsCommand command) {
        if (userService.getById(command.getUserId()) == null) {
            throw new ServiceRuntimeException("User not exists!!");
        }

        for (String ticketId : command.getTicketIds()) {
            if (ticketService.getById(ticketId) != null) {
                BookTicketCommand bookTicketCommand = new BookTicketCommand(ticketId, command.getUserId());
                commandGateway.send(bookTicketCommand);
            }
        }
    }

    @CommandHandler
    public void handleGenerateFilmTickets(GenerateFilmTicketsCommand command) {
        Film film = filmService.getById(command.getFilmId());
        if (film == null) {
            throw new ServiceRuntimeException("Film with id = " + command.getFilmId() + " not exists!!");
        }

        for (int i = 1; i <= command.getPlaceCount(); i++) {
            CreateTicketCommand createTicketCommand = new CreateTicketCommand(UUID.randomUUID().toString(), film.getId(),
                    command.getTicketsCategory(), i, TicketStatus.FREE);
            commandGateway.send(createTicketCommand);
        }
    }
}
