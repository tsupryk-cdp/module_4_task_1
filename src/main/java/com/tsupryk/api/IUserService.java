package com.tsupryk.api;

import com.tsupryk.api.entity.User;

import java.util.List;

/**
 * The interface IUserService.
 * <p/>
 * User: Vitaliy
 * Date: 22.10.13
 */
public interface IUserService {

    void createUser(User user);

    User getUserById(Integer id);

    void init();

    List<User> getAllUsers();
}
