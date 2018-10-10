package com.magistr.duck.service;

import java.util.List;

import com.magistr.duck.entity.User;

public interface UserService {

    User getUser(Integer id);

    User getUser(String name);

    List<User> getUsers();

    void saveUser(User user, CharSequence password);

    void removeUser(Integer id);
}
