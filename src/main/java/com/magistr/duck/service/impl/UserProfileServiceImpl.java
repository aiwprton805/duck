package com.magistr.duck.service.impl;

import com.magistr.duck.dao.UserProfileDao;
import com.magistr.duck.dto.UserProfile;
import com.magistr.duck.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserProfileServiceImpl implements UserProfileService {

    private final UserProfileDao userProfileDao;

    @Autowired
    public UserProfileServiceImpl(UserProfileDao userProfileDao) {
        this.userProfileDao = userProfileDao;
    }

    @Override
    public List<UserProfile> getGuests() {
        return userProfileDao.findByRoleName("guest");
    }

    @Override
    public List<UserProfile> getStudents() {
        return userProfileDao.findByRoleName("student");
    }

    @Override
    public List<UserProfile> getLectors() {
        return userProfileDao.findByRoleName("lector");
    }

    @Override
    public List<UserProfile> getUserProfiles() {
        return userProfileDao.findAll();
    }
}
//class
