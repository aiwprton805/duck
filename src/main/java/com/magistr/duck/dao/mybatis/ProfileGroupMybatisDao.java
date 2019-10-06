package com.magistr.duck.dao.mybatis;

import com.magistr.duck.dao.ProfileGroupDao;
import com.magistr.duck.entity.ProfileGroup;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ProfileGroupMybatisDao extends SqlSessionDaoSupport implements ProfileGroupDao {

    private static final String MAPPER_NAMESPACE = "com.magistr.duck.dao.ProfileGroupDao";

    @Override
    public void create(ProfileGroup group) {
        getSqlSession().insert(MAPPER_NAMESPACE + ".create", group);
    }

    @Override
    public Optional<ProfileGroup> read(Integer id) {
        return Optional.ofNullable(getSqlSession().selectOne(MAPPER_NAMESPACE + ".read", id));
    }

    @Override
    public void update(ProfileGroup group) {
        getSqlSession().update(MAPPER_NAMESPACE + ".update", group);
    }

    @Override
    public void delete(Integer id) {
        getSqlSession().delete(MAPPER_NAMESPACE + ".delete", id);
    }

    @Override
    public void addProfile(Integer profileId, Integer groupId) {
        getSqlSession().insert(MAPPER_NAMESPACE + ".addProfile",
                Map.<String, Integer>of("profileId", profileId, "groupId", groupId));
    }

    @Override
    public void deleteProfile(Integer profileId, Integer groupId) {
        getSqlSession().delete(MAPPER_NAMESPACE + ".deleteProfile",
                Map.<String, Integer>of("profileId", profileId, "groupId", groupId));
    }

    @Override
    public Optional<ProfileGroup> findByToken(String token) {
        return Optional.ofNullable(getSqlSession().selectOne(MAPPER_NAMESPACE + ".findByToken", token));
    }

    @Override
    public List<ProfileGroup> findByProfileId(Integer profileId) {
        return getSqlSession().selectList(MAPPER_NAMESPACE + ".findByProfileId", profileId);
    }

    @Override
    public List<ProfileGroup> findByProfileIdAndRoleName(Integer profileId, String roleName) {
        if(!roleName.equals("admin") && !roleName.equals("lector") && !roleName.equals("student") && !roleName.equals("guest")){
            throw new IllegalArgumentException("Argument roleName must have one of the following values: admin, lector, student, guest.");
        }
        return getSqlSession().selectList(MAPPER_NAMESPACE + ".findByProfileIdAndRoleName",
                Map.of("profileId", profileId, "roleName", roleName));
    }
}
