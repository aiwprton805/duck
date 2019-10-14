package com.magistr.duck.service;

import com.magistr.duck.dto.UserProfile;

import java.util.List;

public interface UserProfileService {

    List<UserProfile> getGuests();

    List<UserProfile> getStudents();

    List<UserProfile> getLectors();

    List<UserProfile> getUserProfiles();
}
