package com.magistr.duck.service;

import com.magistr.duck.entity.Profile;
import com.magistr.duck.entity.ProfileGroup;
import com.magistr.duck.entity.Term;
import com.magistr.duck.entity.User;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

public interface ProfileService {

    void addTerm(Integer profileId, Integer termId);

    void addTerm(Profile profile, Term term);

    Optional<Profile> getProfile(Integer id);

    Optional<Profile> getProfile(Profile profile);

    Optional<Profile> getProfile(User user);

    Optional<Profile> getProfile(Principal principal);

    List<Profile> getLectorProfiles(Integer profileGroupId);

    List<Profile> getStudentProfiles(Integer profileGroupId);

    List<Profile> getLectorProfiles(ProfileGroup profileGroup);

    List<Profile> getStudentProfiles(ProfileGroup profileGroup);

    void saveProfile(Profile profile, Principal principal);

    void removeProfile(Integer id);

    void removeProfile(Profile profile);

    void removeTerm(Integer profileId, Integer termId);

    void removeTerm(Profile profile, Term term);
}
