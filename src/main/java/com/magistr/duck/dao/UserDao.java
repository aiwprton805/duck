package com.magistr.duck.dao;

import java.util.List;
import java.util.Optional;

import com.magistr.duck.entity.User;

public interface UserDao extends CrudDao<Integer, User> {

    Optional<User> findByName(String name);

    List<User> findAll();
}
