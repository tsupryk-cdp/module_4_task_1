package com.tsupryk.domain.handler;

import com.tsupryk.api.ServiceRuntimeException;
import com.tsupryk.api.commands.BookUserTicketsCommand;
import com.tsupryk.api.commands.CreateSeanceTicketsCommand;
import com.tsupryk.domain.aggregate.Seance;
import com.tsupryk.domain.entity.Film;
import com.tsupryk.domain.service.FilmService;
import com.tsupryk.domain.service.UserService;
import org.axonframework.commandhandling.annotation.CommandHandler;
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
public class SeanceCommandHandler {

    @Resource(name = "seanceES")
    private EventSourcingRepository<Seance> repository;

    @Autowired
    private UserService userService;

    @Autowired
    FilmService filmService;

    @CommandHandler
    public void handleCreateSeanceTickets(CreateSeanceTicketsCommand command) {
        Film film = filmService.getById(command.getFilmId());
        if (film == null) {
            throw new ServiceRuntimeException("Film with id = " + command.getFilmId() + " not exists!!");
        }

        command.setSeanceId(UUID.randomUUID().toString());
        Seance ticket = new Seance(command);
        repository.add(ticket);
    }

    @CommandHandler
    public void handleBookUserTickets(BookUserTicketsCommand command) {
        if (userService.getById(command.getUserId()) == null) {
            throw new ServiceRuntimeException("User not exists!!");
        }

        Seance seance = repository.load(command.getSeanceId());
        seance.book(command);
    }

}
