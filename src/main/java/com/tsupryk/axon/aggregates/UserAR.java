package com.tsupryk.axon.aggregates;

import com.tsupryk.axon.commands.CreateUserCommand;
import com.tsupryk.axon.events.UserCreatedEvent;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.domain.DomainEventMessage;
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

    private String firstName;

    private String lastName;

    public UserAR() {
    }

    @CommandHandler
    public UserAR(CreateUserCommand command) {
        UserCreatedEvent event = new UserCreatedEvent(command.getUserId(), command.getFirstName(), command.getLastName());
//        id = command.getUserId();
        apply(event);
    }

    @EventHandler
    public void on(UserCreatedEvent event) {
        this.id = event.getUserId();
        this.firstName = event.getFirstName();
        this.lastName = event.getLastName();
    }
}
