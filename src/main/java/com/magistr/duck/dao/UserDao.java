package com.magistr.duck.dao;

import java.util.List;

import com.magistr.duck.entity.User;

public interface UserDao extends CrudDao<Integer, User> {

    User findByName(String name);

    List<User> findAll();
}
