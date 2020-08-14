package com.magistr.duck.service.impl;

import com.magistr.duck.dao.LectorProfileDao;
import com.magistr.duck.dao.ProfileDao;
import com.magistr.duck.dto.LectorProfile;
import com.magistr.duck.entity.Profile;
import com.magistr.duck.entity.ProfileGroup;
import com.magistr.duck.entity.Term;
import com.magistr.duck.entity.User;
import com.magistr.duck.service.ProfileService;
import com.magistr.duck.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
public class ProfileServiceImpl implements ProfileService {

    private final static Logger LOGGER = LoggerFactory.getLogger(ProfileServiceImpl.class);

    private final ProfileDao profileDao;
    private final LectorProfileDao lectorProfileDao;
    private final UserService userService;

    @Autowired
    public ProfileServiceImpl(ProfileDao profileDao, LectorProfileDao lectorProfileDao, UserService userService) {
        this.profileDao = profileDao;
        this.lectorProfileDao = lectorProfileDao;
        this.userService = userService;
    }

    @Override
    public void addTerm(Integer profileId, Integer termId) {
        profileDao.addTerm(profileId, termId);
    }

    @Override
    public void addTerm(Profile profile, Term term) {
        if (profile.getId() == null || term.getId() == null) {
            LOGGER.warn("ProfileServiceImpl.addTerm didn't add the term");
            throw new IllegalArgumentException("profile.id and term.id must be not null");
        } else {
            profileDao.addTerm(profile.getId(), term.getId());
        }
    }

    @Override
    public Optional<Profile> getProfile(Integer id) {
        return profileDao.read(id);
    }

    @Override
    public Optional<Profile> getProfile(Profile profile) {
        return profileDao.read(profile.getId());
    }

    @Override
    public Optional<Profile> getProfile(User user) {
        Optional<Profile> result;
        if (user.getId() != null) {
            result = profileDao.findByUserId(user.getId());
        } else if (user.getName() != null) {
            result = profileDao.findByUserName(user.getName());
        } else {
            result = Optional.empty();
        }
        return result;
    }

    @Override
    public Optional<Profile> getProfile(Principal principal) {
        return profileDao.findByUserName(principal.getName());
    }

    @Override
    public List<LectorProfile> getLectorProfiles() {
        return lectorProfileDao.findAll();
    }

    @Override
    public List<Profile> getLectorProfiles(Integer profileGroupId) {
        if (profileGroupId == null) {
            throw new IllegalArgumentException("profileGroupId must be not null");
        }
        return profileDao.findByRoleNameAndProfileGroupId("lector", profileGroupId);
    }

    @Override
    public List<Profile> getStudentProfiles(Integer profileGroupId) {
        if (profileGroupId == null) {
            throw new IllegalArgumentException("profileGroupId must be not null");
        }
        return profileDao.findByRoleNameAndProfileGroupId("student", profileGroupId);
    }

    @Override
    public List<Profile> getLectorProfiles(ProfileGroup profileGroup) {
        return getLectorProfiles(profileGroup.getId());
    }

    @Override
    public List<Profile> getStudentProfiles(ProfileGroup profileGroup) {
        return getStudentProfiles(profileGroup.getId());
    }

    @Override
    public void saveProfile(Profile profile, Principal principal) {
        if (profile.getUserId() == null) {
            var user = userService.getUser(principal.getName()).orElseGet(User::new);
            profile.setUserId(user.getId());
        }
        if (profile.getId() == null) {
            profileDao.create(profile);
        } else {
            profileDao.update(profile);
        }
    }

    @Override
    public void removeProfile(Integer id) {
        profileDao.delete(id);
    }

    @Override
    public void removeProfile(Profile profile) {
        profileDao.delete(profile.getId());
    }

    @Override
    public void removeTerm(Integer profileId, Integer termId) {
        profileDao.deleteTerm(profileId, termId);
    }

    @Override
    public void removeTerm(Profile profile, Term term) {
        if (profile.getId() == null || term.getId() == null) {
            LOGGER.warn("ProfileServiceImpl.removeTerm didn't remove the term");
            throw new IllegalArgumentException("profile.id and term.id must be not null");
        } else {
            profileDao.deleteTerm(profile.getId(), term.getId());
        }
    }
}
//class
