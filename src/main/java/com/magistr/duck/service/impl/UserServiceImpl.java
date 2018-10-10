package com.magistr.duck.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.magistr.duck.dao.UserDao;
import com.magistr.duck.entity.Role;
import com.magistr.duck.entity.User;
import com.magistr.duck.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    private final static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User getUser(Integer id) {
        return userDao.read(id);
    }

    @Override
    public User getUser(String name) {
        return userDao.findByName(name);
    }

    @Override
    public List<User> getUsers() {
        return userDao.findAll();
    }

    @Override
    public void saveUser(User user, CharSequence password) {
        if (password != null) {
            user.setPassword(passwordEncoder.encode(password));
        }

        var dbUser = userDao.findByName(user.getName());
        if (dbUser == null) {
            List<Role> roles = List.of(new Role("user"));
            user.setRoles(roles);
            userDao.create(user);
        } else {
            userDao.update(user);
        }
        logger.debug("saveUser: {}", user);
    }

    @Override
    public void removeUser(Integer id) {
        userDao.delete(id);
    }

}
