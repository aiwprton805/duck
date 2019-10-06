package com.magistr.duck.dao.mybatis;

import com.magistr.duck.dao.TermGroupDao;
import com.magistr.duck.entity.TermGroup;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import java.util.List;
import java.util.Optional;

public class TermGroupMybatisDao extends SqlSessionDaoSupport implements TermGroupDao {

    private static final String MAPPER_NAMESPACE = "com.magistr.duck.dao.TermGroupDao";

    @Override
    public void create(TermGroup termGroup) {
        getSqlSession().insert(MAPPER_NAMESPACE + ".create", termGroup);
    }

    @Override
    public Optional<TermGroup> read(Integer id) {
        return Optional.ofNullable(getSqlSession().selectOne(MAPPER_NAMESPACE + ".read", id));
    }

    @Override
    public void update(TermGroup termGroup) {
        getSqlSession().update(MAPPER_NAMESPACE + ".update", termGroup);
    }

    @Override
    public void delete(Integer id) {
        getSqlSession().delete(MAPPER_NAMESPACE + ".delete", id);
    }

    @Override
    public Optional<TermGroup> findByTermGroupId(Integer termGroupId) {
        return Optional.ofNullable(getSqlSession()
                .selectOne(MAPPER_NAMESPACE + ".findByTermGroupId", termGroupId));
    }

    @Override
    public List<TermGroup> findAll() {
        return getSqlSession().selectList(MAPPER_NAMESPACE + ".findAll");
    }

    @Override
    public List<TermGroup> findLastCompletedGroups(Integer limit) {
        return getSqlSession().selectList(MAPPER_NAMESPACE + ".findLastCompletedGroups", limit);
    }

}
//class
