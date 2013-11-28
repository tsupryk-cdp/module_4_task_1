package com.tsupryk.view.service;

import com.tsupryk.view.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;

/**
 * The class UserService.
 * <p/>
 * Date: 26.11.13
 * <p/>
 * Author: Vitaliy
 */
@Service
public class UserService {

    public static final String COLLECTION_USERS = "users";

    @Autowired
    MongoOperations repository;

    public User getById(String id) {
        return repository.findById(id, User.class, COLLECTION_USERS);
    }
}
