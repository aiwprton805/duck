package com.magistr.duck.dao;

import com.magistr.duck.dto.UserProfile;

import java.util.List;

public interface UserProfileDao {

    List<UserProfile> findByRoleName(String roleName);

    List<UserProfile> findAll();
}
