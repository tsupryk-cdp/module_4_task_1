package com.tsupryk.axon.commands;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

/**
 * The class CreateUserCommand.
 * <p/>
 * Date: 17.11.13
 * <p/>
 * Author: Vitaliy
 */
public class CreateUserCommand {

    @TargetAggregateIdentifier
    private String userId;

    private String firstName;

    private String lastName;

    public CreateUserCommand() {

    }

    public CreateUserCommand(String userId, String firstName, String lastName) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getUserId() {
        return userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
