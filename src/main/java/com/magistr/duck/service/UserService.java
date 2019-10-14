package com.magistr.duck.service;

import java.util.List;
import java.util.Optional;

import com.magistr.duck.entity.User;

public interface UserService {

    Optional<User> getUser(Integer id);

    Optional<User> getUser(String name);

    List<User> getUsers();

    void saveUser(User user);

    void saveUser(User user, CharSequence password);

    void removeUser(Integer id);
}
