package com.magistr.duck.service.impl;

import com.magistr.duck.common.exceptions.AddProfileToProfileGroupException;
import com.magistr.duck.common.exceptions.ProfileGroupNotFoundException;
import com.magistr.duck.dao.ProfileGroupDao;
import com.magistr.duck.entity.Entity;
import com.magistr.duck.entity.Profile;
import com.magistr.duck.entity.ProfileGroup;
import com.magistr.duck.service.ProfileGroupService;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfileGroupServiceImpl implements ProfileGroupService {

    private final static Logger LOGGER = LoggerFactory.getLogger(ProfileGroupServiceImpl.class);

    private final ProfileGroupDao groupDao;

    @Autowired
    public ProfileGroupServiceImpl(ProfileGroupDao groupDao) {
        this.groupDao = groupDao;
    }

    @Override
    public void addProfile(Integer profileId, Integer groupId) {
        groupDao.addProfile(profileId, groupId);
    }

    @Override
    public void addProfile(Profile profile, ProfileGroup group) {
        if(profile.getId() == null || group.getId() == null){
            LOGGER.warn("ProfileGroupServiceImpl.addProfile didn't add the profile");
            throw new IllegalArgumentException("profile.id and group.id must be not null");
        }else{
            groupDao.addProfile(profile.getId(), group.getId());
        }
    }

    @Override
    public ProfileGroup addProfile(final Profile profile, final String token) {
        ProfileGroup group = groupDao.findByToken(token).orElseThrow(ProfileGroupNotFoundException::new);
        if(group.getProfiles().stream().map(Profile::getId).anyMatch(profileId -> profileId.equals(profile.getId()))){
            throw new AddProfileToProfileGroupException();
        }
        addProfile(profile, group);
        return group;
    }

    @Override
    public Optional<ProfileGroup> getProfileGroup(Integer id) {
        return groupDao.read(id);
    }

    @Override
    public Optional<ProfileGroup> getProfileGroup(ProfileGroup group) {
        return groupDao.read(group.getId());
    }

    @Override
    public List<ProfileGroup> getProfileGroups(Profile profile) {
        return groupDao.findByProfileId(profile.getId());
    }

    @Override
    public List<ProfileGroup> getLectorProfileGroups(Profile profile) {
        return groupDao.findByProfileIdAndRoleName(profile.getId(), "lector");
    }

    @Override
    public List<ProfileGroup> getStudentProfileGroups(Profile profile) {
        return groupDao.findByProfileIdAndRoleName(profile.getId(), "student");
    }

    @Override
    public void saveProfileGroup(ProfileGroup group) {
        if(group.getId() == null){
            groupDao.create(group);
        }else{
            groupDao.update(group);
        }
    }

    @Override
    public void removeProfileGroup(Integer id) {
        groupDao.delete(id);
    }

    @Override
    public void removeProfileGroup(ProfileGroup group) {
        groupDao.delete(group.getId());
    }

    @Override
    public void removeProfile(Integer profileId, Integer groupId) {
        groupDao.deleteProfile(profileId, groupId);
    }

    @Override
    public void removeProfile(Profile profile, ProfileGroup group) {
        groupDao.deleteProfile(profile.getId(), group.getId());
    }

    @Override
    public String generateToken() {
        var token = RandomStringUtils.randomAlphanumeric(6);
        while(groupDao.findByToken(token).isPresent()){
            token = RandomStringUtils.randomAlphanumeric(6);
        }
        return token;
    }
}
//class
