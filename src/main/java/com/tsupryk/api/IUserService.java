package com.tsupryk.api;

import com.tsupryk.api.entity.User;
import com.tsupryk.axon.aggregates.UserAR;

import java.util.List;

/**
 * The interface IUserService.
 * <p/>
 * User: Vitaliy
 * Date: 22.10.13
 */
public interface IUserService {

    void createUser(User user);

    UserAR getUserById(String id);

    void init();

    List<User> getAllUsers();
}
