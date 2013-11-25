package com.tsupryk.axon.commands;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

/**
 * The class CreateUserCommand.
 * <p/>
 * Date: 17.11.13
 * <p/>
 * Author: Vitaliy
 */
public class CreateUserCommand implements Command {

    @TargetAggregateIdentifier
    private String userId;

    private String firstName;

    private String lastName;

    private String password;

    public CreateUserCommand() {

    }

    public CreateUserCommand(String userId, String firstName, String lastName, String password) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

}
