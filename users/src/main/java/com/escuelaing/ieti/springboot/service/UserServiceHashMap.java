package com.escuelaing.ieti.springboot.service;

import com.escuelaing.ieti.springboot.entities.User;
import org.springframework.stereotype.Service;

import java.util.*;

//@Service
public class UserServiceHashMap implements UserService{
    private HashMap<String, User> users = new HashMap<String, User>();

    @Override
    public User create(User user) {
        if (users.containsKey(user.getId())) {
            return null;
        } else {
            users.put(user.getId(), user);
            return users.get(user.getId());
        }
    }

    @Override
    public User findById(String id) {
        return users.get(Integer.parseInt(id));
    }

    @Override
    public List<User> getAll() {
        Collection<User> values = users.values();
        return new ArrayList<User>(values);
    }

    @Override
    public boolean deleteById(String id) {
        User userTemp = users.remove(Integer.parseInt(id));
        return userTemp != null;
    }

    @Override
    public User update(User user, String userId) {
        if (users.containsKey(Integer.parseInt(userId))) {
            users.replace(userId, user);
        }
        return users.get(Integer.parseInt(userId));
    }


    @Override
    public List<User> findUsersWithNameOrLastNameLike(String queryText) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<User> findUsersCreatedAfter(String startDate) {
        throw new UnsupportedOperationException();
    }

    @Override
    public User findByEmail(String email) {
        throw new UnsupportedOperationException();
    }
}
