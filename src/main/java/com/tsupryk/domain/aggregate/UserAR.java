package com.tsupryk.domain.aggregate;

import com.tsupryk.api.commands.CreateUserCommand;
import com.tsupryk.api.events.UserCreatedEvent;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;

/**
 * The class UserAR.
 * <p/>
 * Date: 26.11.13
 * <p/>
 * Author: Vitaliy
 */
public class UserAR extends AbstractAnnotatedAggregateRoot<String> {

    @AggregateIdentifier
    private String id;

    private String firstName;

    private String lastName;

    public UserAR() {
        
    }

    public UserAR(CreateUserCommand command) {
        UserCreatedEvent event = new UserCreatedEvent(command.getUserId(), command.getFirstName(),
                command.getLastName());

        apply(event);
    }

    @EventHandler
    public void on(UserCreatedEvent event) {
        id = event.getUserId();
        firstName = event.getFirstName();
        lastName = event.getLastName();
    }
}
