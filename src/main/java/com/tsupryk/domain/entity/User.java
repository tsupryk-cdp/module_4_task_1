package com.tsupryk.domain.entity;

/**
 * The class User.
 * <p/>
 * Date: 26.11.13
 * <p/>
 * Author: Vitaliy
 */
public class User {

    private String id;

    private String firstName;

    private String lastName;

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
}