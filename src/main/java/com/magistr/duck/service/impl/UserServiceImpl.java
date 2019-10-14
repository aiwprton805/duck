package com.magistr.duck.service.impl;

import java.util.List;
import java.util.Optional;

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

    private final static Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserDao userDao;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserDao userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<User> getUser(Integer id) {
        return userDao.read(id);
    }

    @Override
    public Optional<User> getUser(String name) {
        return userDao.findByName(name);
    }

    @Override
    public List<User> getUsers() {
        return userDao.findAll();
    }

    @Override
    public void saveUser(User user) {
        var optUser = userDao.findByName(user.getName());
        if(optUser.isEmpty()){
            List<Role> roles = List.of(new Role("guest"));
            user.setRoles(roles);
            userDao.create(user);
        } else {
            userDao.update(user);
        }
        LOGGER.debug("saveUser: {}", user);
    }

    @Override
    public void saveUser(User user, CharSequence password) {
        if (password != null) {
            user.setPassword(passwordEncoder.encode(password));
        }
        saveUser(user);
    }

    @Override
    public void removeUser(Integer id) {
        userDao.delete(id);
    }

}
//class
