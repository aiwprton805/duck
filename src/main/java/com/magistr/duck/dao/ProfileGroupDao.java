package com.magistr.duck.dao;

import com.magistr.duck.entity.ProfileGroup;

import java.util.List;
import java.util.Optional;

public interface ProfileGroupDao extends CrudDao<Integer, ProfileGroup> {

    void addProfile(Integer profileId, Integer groupId);

    void deleteProfile(Integer profileId, Integer groupId);

    Optional<ProfileGroup> findByToken(String token);

    List<ProfileGroup> findByProfileId(Integer profileId);

    List<ProfileGroup> findByProfileIdAndRoleName(Integer profileId, String roleName);
}
