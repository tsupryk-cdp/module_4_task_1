package com.tsupryk.domain.handler;

import com.tsupryk.api.commands.CreateUserCommand;
import com.tsupryk.domain.aggregate.UserAR;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * The class FilmCommandHandler.
 * <p/>
 * Date: 26.11.13
 * <p/>
 * Author: Vitaliy
 */
@Component
public class UserCommandHandler {

    @Resource(name = "userES")
    private EventSourcingRepository<UserAR> repository;

    @CommandHandler
    public void handleCreateFilm(CreateUserCommand command) {
        UserAR user = new UserAR(command);
        repository.add(user);
    }

}
