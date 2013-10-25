package com.tsupryk.service;

import com.tsupryk.api.IUserRepository;
import com.tsupryk.api.IUserService;
import com.tsupryk.api.entity.Ticket;
import com.tsupryk.api.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * The class UserService.
 * <p/>
 * User: Vitaliy
 * Date: 22.10.13
 */
@Service
@Transactional
public class UserService implements IUserService {

    @Autowired
    IUserRepository repository;

    @Override
    public void createUser(User user) {
        user.setId(null);
        user.setUserTickets(null);
        repository.insert(user);
    }

    @Override
    public User getUserById(Integer id) {
        return repository.getById(id);
    }

    @Override
    public void init() {
        repository.init();
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = repository.getAllUsers();
        for (User user : users) {
            if (!CollectionUtils.isEmpty(user.getUserTickets())) {
                for (Ticket ticket : user.getUserTickets()) {
                    ticket.setUser(null);
                }
            };
        }
        return users;
    }
}
