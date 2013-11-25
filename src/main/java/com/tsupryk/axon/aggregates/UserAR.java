package com.tsupryk.axon.aggregates;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.tsupryk.axon.commands.ChangePasswordCommand;
import com.tsupryk.axon.commands.CreateUserCommand;
import com.tsupryk.axon.events.PasswordChangedEvent;
import com.tsupryk.axon.events.UserCreatedEvent;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.domain.DomainEventMessage;
import org.axonframework.domain.DomainEventStream;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;

import java.beans.Transient;
import java.util.UUID;

/**
 * The class UserAR.
 * <p/>
 * Date: 17.11.13
 * <p/>
 * Author: Vitaliy
 */
@JsonAutoDetect(getterVisibility = JsonAutoDetect.Visibility.NONE, fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonSerialize(include= JsonSerialize.Inclusion.NON_DEFAULT)
public class UserAR extends AbstractAnnotatedAggregateRoot {

    @AggregateIdentifier
    private String id;

    private String firstName;

    private String lastName;

    private String password;

    public UserAR() {
    }

    public UserAR(String id, String firstName, String lastName, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
    }

    @CommandHandler
    public UserAR(CreateUserCommand command) {
        command.setUserId(UUID.randomUUID().toString());
        UserCreatedEvent event = new UserCreatedEvent(command.getUserId(), command.getFirstName(), command.getLastName(), command.getPassword());
        apply(event);
    }

    @CommandHandler
    public void handle(ChangePasswordCommand command) {
        apply(new PasswordChangedEvent(command.getUserId(), command.getNewPassword()));
    }

    @EventHandler
    public void on(UserCreatedEvent event) {
        this.id = event.getUserId();
        this.firstName = event.getFirstName();
        this.lastName = event.getLastName();
        this.password = event.getPassword();
    }

    @EventHandler
    public void on(PasswordChangedEvent event) {
        this.password = event.getNewPassword();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void changePassword(String newPassword) {
        apply(new PasswordChangedEvent(id, password));
    }
}
