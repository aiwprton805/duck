package com.magistr.duck.dao;

import com.magistr.duck.entity.Profile;

import java.util.List;
import java.util.Optional;

public interface ProfileDao extends CrudDao<Integer, Profile> {

    void addTerm(Integer profileId, Integer termId);

    void deleteTerm(Integer profileId, Integer termId);

    Optional<Profile> findByUserId(Integer userId);

    Optional<Profile> findByUserName(String userName);

    List<Profile> findByRoleNameAndProfileGroupId(String roleName, Integer profileGroupId);
}
