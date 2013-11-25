package com.tsupryk.axon.handler;

import com.tsupryk.axon.aggregates.UserAR;
import com.tsupryk.axon.commands.ChangePasswordCommand;
import com.tsupryk.axon.commands.CreateUserCommand;
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
public class UserCommandHandler {

//    @Autowired
//    private IUserRepository repository;

    @Autowired
    private EventSourcingRepository<UserAR> repository;


    @CommandHandler
    public void handleCreateUser(CreateUserCommand command) {
        UserAR user = new UserAR(command);
        repository.add(user);
    }

    @CommandHandler
    public void handleChangePassword(ChangePasswordCommand command) {
        UserAR user = repository.load(command.getUserId());
        user.changePassword(command.getNewPassword());
        //repository.add(user);
    }
}
