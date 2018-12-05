package com.magistr.duck.dao.mybatis;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.magistr.duck.dao.UserDao;
import com.magistr.duck.entity.User;

public class UserMybatisDao extends SqlSessionDaoSupport implements UserDao {

    private static final String MAPPER_NAMESPACE = "com.magistr.duck.dao.mybatis.mappers.UserMapper";

    @Override
    public void create(User user) {
        getSqlSession().insert(MAPPER_NAMESPACE + ".create", user);
    }

    @Override
    public User read(Integer id) {
        return getSqlSession().selectOne(MAPPER_NAMESPACE + ".read", id);
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
    public User findByName(String name) {
        return getSqlSession().selectOne(MAPPER_NAMESPACE + ".findByName", name);
    }

    @Override
    public List<User> findAll() {
        return getSqlSession().selectList(MAPPER_NAMESPACE + ".findAll");
    }

}
