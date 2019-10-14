package com.magistr.duck.dao;

import com.magistr.duck.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserDao extends CrudDao<Integer, User> {

    Optional<User> findByName(String name);

    List<User> findAll();
}
