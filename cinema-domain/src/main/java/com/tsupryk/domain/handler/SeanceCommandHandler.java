package com.tsupryk.domain.handler;

import com.tsupryk.api.commands.BookUserTicketsCommand;
import com.tsupryk.api.commands.CreateSeanceTicketsCommand;
import com.tsupryk.domain.ServiceRuntimeException;
import com.tsupryk.domain.aggregate.FilmAR;
import com.tsupryk.domain.aggregate.SeanceAR;
import com.tsupryk.domain.aggregate.UserAR;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

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
    private EventSourcingRepository<SeanceAR> repository;

    @Resource(name = "filmES")
    private EventSourcingRepository<FilmAR> filmRepository;

    @Resource(name = "userES")
    private EventSourcingRepository<UserAR> userRepository;


    @CommandHandler
    public void handleCreateSeanceTickets(CreateSeanceTicketsCommand command) {
        checkThatFilmExist(command);
        repository.add(new SeanceAR(command));
    }

    private void checkThatFilmExist(CreateSeanceTicketsCommand command) {
        FilmAR film = filmRepository.load(command.getFilmId());
        if (film == null) {
            throw new ServiceRuntimeException("Film with id = " + command.getFilmId() + " not exists!!");
        }
    }

    @CommandHandler
    public void handleBookUserTickets(BookUserTicketsCommand command) {
        if (userRepository.load(command.getUserId()) == null) {
            throw new ServiceRuntimeException("User not exists!!");
        }

        SeanceAR seanceAR = repository.load(command.getSeanceId());
        seanceAR.book(command);
    }

}
