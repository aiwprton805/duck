package com.magistr.duck.service;

import com.magistr.duck.entity.Profile;
import com.magistr.duck.entity.ProfileGroup;

import java.util.List;
import java.util.Optional;

public interface ProfileGroupService {

    void addProfile(Integer profileId, Integer groupId);

    void addProfile(Profile profile, ProfileGroup group);

    ProfileGroup addProfile(Profile profile, String token);

    Optional<ProfileGroup> getProfileGroup(Integer id);

    Optional<ProfileGroup> getProfileGroup(ProfileGroup group);

    List<ProfileGroup> getProfileGroups(Profile profile);

    List<ProfileGroup> getLectorProfileGroups(Profile profile);

    List<ProfileGroup> getStudentProfileGroups(Profile profile);

    void saveProfileGroup(ProfileGroup group);

    void removeProfileGroup(Integer id);

    void removeProfileGroup(ProfileGroup group);

    void removeProfile(Integer profileId, Integer groupId);

    void removeProfile(Profile profile, ProfileGroup group);

    String generateToken();
}
