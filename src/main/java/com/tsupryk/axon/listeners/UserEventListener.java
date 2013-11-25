package com.tsupryk.axon.listeners;

import com.tsupryk.axon.aggregates.UserAR;
import com.tsupryk.axon.events.PasswordChangedEvent;
import com.tsupryk.axon.events.UserCreatedEvent;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * The Class UserEventListener.
 * <p/>
 * Date: 21.11.13
 *
 * @author Vitaliy_Tsupryk
 */
@Component
public class UserEventListener {
    public static final String COLLECTION_USERS = "users";
    Map<String, String > passwords;

    @Autowired
    private MongoOperations mongoOperations;

    @EventHandler
    public void handleUserCreated(UserCreatedEvent event) {
        UserAR user = new UserAR();
        user.setId(event.getUserId());
        user.setFirstName(event.getFirstName());
        user.setLastName(event.getLastName());
        user.setPassword(event.getPassword());
        mongoOperations.save(user, COLLECTION_USERS);
    }

    @EventHandler
    public void handlePasswordChanged(PasswordChangedEvent event) {
        passwords.put(event.getUserId().toString(), event.getNewPassword());
    }
}
