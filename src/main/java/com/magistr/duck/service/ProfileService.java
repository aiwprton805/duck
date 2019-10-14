package com.magistr.duck.service;

import com.magistr.duck.entity.Profile;
import com.magistr.duck.entity.Term;
import com.magistr.duck.entity.User;

import java.security.Principal;
import java.util.Optional;

public interface ProfileService {

    void addTerm(Integer profileId, Integer termId);

    void addTerm(Profile profile, Term term);

    Optional<Profile> getProfile(Integer id);

    Optional<Profile> getProfile(Profile profile);

    Optional<Profile> getProfile(User user);

    Optional<Profile> getProfile(Principal principal);

    void saveProfile(Profile profile, Principal principal);

    void removeProfile(Integer id);

    void removeProfile(Profile profile);

    void removeTerm(Integer profileId, Integer termId);

    void removeTerm(Profile profile, Term term);
}
