package com.tsupryk.view.listeners;

import com.tsupryk.view.entity.User;
import com.tsupryk.api.events.UserCreatedEvent;
import com.tsupryk.view.service.UserService;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Component;

/**
 * The class UserEventListener.
 * <p/>
 * Date: 26.11.13
 * <p/>
 * Author: Vitaliy
 */
@Component
public class UserEventListener {

    @Autowired
    MongoOperations mongoOperations;

    @EventHandler
    public void handleUserCreated(UserCreatedEvent event) {
        User user = new User();
        user.setId(event.getUserId());
        user.setFirstName(event.getFirstName());
        user.setLastName(event.getLastName());

        mongoOperations.insert(user, UserService.COLLECTION_USERS);
    }
}
