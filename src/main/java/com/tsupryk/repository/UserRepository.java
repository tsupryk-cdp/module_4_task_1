package com.tsupryk.repository;

import com.tsupryk.api.IUserRepository;
import com.tsupryk.api.entity.User;
import com.tsupryk.axon.aggregates.UserAR;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The class UserRepository.
 * <p/>
 * User: Vitaliy
 * Date: 22.10.13
 */
@Repository
public class UserRepository implements IUserRepository {

    private static final String SELECT_BY_ID = "from com.tsupryk.api.entity.User where id=:id";
    private static final String ID = "id";
    private static final String SELECT_ALL_USERS = "from com.tsupryk.api.entity.User";

    @Autowired
    private SessionFactory factory;

    @Autowired
    private MongoOperations operations;

    @Override
    public UserAR getById(String id) {
        return operations.findById(id, UserAR.class);
    }

    @Override
    public List<User> getAllUsers() {
        return getSession().createQuery(SELECT_ALL_USERS).list();
    }

    @Override
    public void insert(User user) {
        getSession().save(user);
    }

    @Override
    public void init() {
        getSession().createQuery("delete com.tsupryk.api.entity.User").executeUpdate();
        User user = new User();
        user.setFirstName("ff");
        user.setLastName("ll");
        getSession().save(user);
    }

    private Session getSession() {
        return factory.getCurrentSession();
    }
}
