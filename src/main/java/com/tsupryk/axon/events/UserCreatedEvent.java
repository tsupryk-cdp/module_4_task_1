package com.tsupryk.axon.events;

/**
 * The class UserCreatedEvent.
 * <p/>
 * Date: 17.11.13
 * <p/>
 * Author: Vitaliy
 */
public class UserCreatedEvent {

    private String userId;

    private String firstName;

    private String lastName;

    public UserCreatedEvent(String userId, String firstName, String lastName) {
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

}
