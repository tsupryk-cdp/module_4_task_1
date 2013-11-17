package com.tsupryk.axon.aggregates;

import com.tsupryk.axon.commands.CreateUserCommand;
import com.tsupryk.axon.events.UserCreatedEvent;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;

/**
 * The class UserAR.
 * <p/>
 * Date: 17.11.13
 * <p/>
 * Author: Vitaliy
 */
public class UserAR extends AbstractAnnotatedAggregateRoot {

    @AggregateIdentifier
    private String id;

    public UserAR() {
    }

    @CommandHandler
    public UserAR(CreateUserCommand command) {
        apply(new UserCreatedEvent(command.getUserId(), command.getFirstName(), command.getLastName()));
    }

    @EventHandler
    public void on(UserCreatedEvent event) {
        this.id = event.getUserId();
    }
}
