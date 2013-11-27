package com.tsupryk.api.commands;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

/**
 * The class CreateUserCommand.
 * <p/>
 * Date: 26.11.13
 * <p/>
 * Author: Vitaliy
 */
public class CreateUserCommand {

    @TargetAggregateIdentifier
    private String userId;

    private String firstName;

    private String lastName;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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
}