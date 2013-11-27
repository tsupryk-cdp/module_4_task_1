package com.tsupryk.domain.handler;

import com.tsupryk.api.ServiceRuntimeException;
import com.tsupryk.domain.aggregate.Seance;
import com.tsupryk.api.commands.BookTicketCommand;
import com.tsupryk.api.commands.BookUserTicketsCommand;
import com.tsupryk.api.commands.CreateTicketCommand;
import com.tsupryk.api.commands.GenerateFilmTicketsCommand;
import com.tsupryk.domain.entity.Film;
import com.tsupryk.api.TicketStatus;
import com.tsupryk.domain.service.FilmService;
import com.tsupryk.domain.service.TicketService;
import com.tsupryk.domain.service.UserService;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    private EventSourcingRepository<Seance> repository;

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
        Seance ticket = new Seance(command);
        repository.add(ticket);
    }

    @CommandHandler
    public void handleBookTicket(BookTicketCommand command) {
        Seance ticket = repository.load(command.getTicketId());
        ticket.book(command);
    }

    @CommandHandler
    public void handleBookUserTickets(BookUserTicketsCommand command) {
        if (userService.getById(command.getUserId()) == null) {
            throw new ServiceRuntimeException("User not exists!!");
        }

        for (String seanceId : command.getTicketIds()) {
            Seance seance = repository.load(seanceId);
            seance.book(new BookTicketCommand(seanceId, command.getUserId()));
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
