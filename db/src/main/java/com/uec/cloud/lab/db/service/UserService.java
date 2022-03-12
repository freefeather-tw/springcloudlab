package com.uec.cloud.lab.db.service;

import com.uec.cloud.lab.db.entity.User;

import java.util.List;

public interface UserService {

    List<User> list();

    User find(String name);
}
