package com.uec.cloud.lab.db.service.impl;

import com.uec.cloud.lab.db.entity.User;
import com.uec.cloud.lab.db.service.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private List<User> userList = new ArrayList<>();

    public UserServiceImpl() {
        userList.add(new User("Jack", 40));
        userList.add(new User("Tom", 30));
        userList.add(new User("Mary", 20));
    }
    @Override
    public List<User> list() {
        return userList;
    }

    @Override
    public User find(String name) {

        return userList.stream().filter(u -> u.getName().equals(name))
                .findFirst()
                .orElse(null);
    }
}
