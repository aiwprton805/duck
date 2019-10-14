package com.magistr.duck.dao.mybatis;

import com.magistr.duck.dao.UserDao;
import com.magistr.duck.entity.User;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import java.util.List;
import java.util.Optional;

public class UserMybatisDao extends SqlSessionDaoSupport implements UserDao {

    private static final String MAPPER_NAMESPACE = "com.magistr.duck.dao.UserDao";

    @Override
    public void create(User user) {
        getSqlSession().insert(MAPPER_NAMESPACE + ".create", user);
    }

    @Override
    public Optional<User> read(Integer id) {
        return Optional.ofNullable(getSqlSession().selectOne(MAPPER_NAMESPACE + ".read", id));
    }

    @Override
    public void update(User user) {
        getSqlSession().update(MAPPER_NAMESPACE + ".update", user);
    }

    @Override
    public void delete(Integer id) {
        getSqlSession().delete(MAPPER_NAMESPACE + ".delete", id);
    }

    @Override
    public Optional<User> findByName(String name) {
        return Optional.ofNullable(getSqlSession().selectOne(MAPPER_NAMESPACE + ".findByName", name));
    }

    @Override
    public List<User> findAll() {
        return getSqlSession().selectList(MAPPER_NAMESPACE + ".findAll");
    }

}
//class
