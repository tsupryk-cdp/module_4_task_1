package com.tsupryk.api;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * The class User.
 * <p/>
 * User: Vitaliy
 * Date: 22.10.13
 */
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String firstName;

    private String lastName;

    @OneToMany(fetch = FetchType.LAZY)
    private Set<Ticket> userTickets;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Set<Ticket> getUserTickets() {
        return userTickets;
    }

    public void setUserTickets(Set<Ticket> userTickets) {
        this.userTickets = userTickets;
    }
}
