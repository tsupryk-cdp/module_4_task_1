package com.tsupryk.api;

import com.tsupryk.api.entity.User;
import com.tsupryk.axon.aggregates.UserAR;

import java.util.List;

/**
 * The interface IUserRepository.
 * <p/>
 * User: Vitaliy
 * Date: 22.10.13
 */
public interface IUserRepository {

//    User getById(Integer id);

    void insert(User user);

    void init();

    List<User> getAllUsers();

    UserAR getById(String id);
}
