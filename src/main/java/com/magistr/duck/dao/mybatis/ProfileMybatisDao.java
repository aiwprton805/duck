package com.magistr.duck.dao.mybatis;

import com.magistr.duck.dao.ProfileDao;
import com.magistr.duck.entity.Profile;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import java.util.Map;
import java.util.Optional;

public class ProfileMybatisDao extends SqlSessionDaoSupport implements ProfileDao {

    private static final String MAPPER_NAMESPACE = "com.magistr.duck.dao.ProfileDao";

    @Override
    public void create(Profile profile) {
        getSqlSession().insert(MAPPER_NAMESPACE + ".create", profile);
    }

    @Override
    public Optional<Profile> read(Integer id) {
        return Optional.ofNullable(getSqlSession().selectOne(MAPPER_NAMESPACE + ".read", id));
    }

    @Override
    public void update(Profile profile) {
        getSqlSession().update(MAPPER_NAMESPACE + ".update", profile);
    }

    @Override
    public void delete(Integer id) {
        getSqlSession().delete(MAPPER_NAMESPACE + ".delete", id);
    }

    @Override
    public void addTerm(Integer profileId, Integer termId) {
        getSqlSession().insert(MAPPER_NAMESPACE + ".addTerm",
                Map.of("profileId", profileId, "termId", termId));
    }

    @Override
    public void deleteTerm(Integer profileId, Integer termId) {
        getSqlSession().delete(MAPPER_NAMESPACE + ".deleteTerm",
                Map.of("profileId", profileId, "termId", termId));
    }

    @Override
    public Optional<Profile> findByUserId(Integer userId) {
        return Optional.ofNullable(getSqlSession().selectOne(MAPPER_NAMESPACE + ".findByUserId", userId));
    }

    @Override
    public Optional<Profile> findByUserName(String userName) {
        return Optional.ofNullable(getSqlSession().selectOne(MAPPER_NAMESPACE + ".findByUserName", userName));
    }
}
//class
