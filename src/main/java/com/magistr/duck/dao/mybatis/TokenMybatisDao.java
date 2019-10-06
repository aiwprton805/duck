package com.magistr.duck.dao.mybatis;

import com.magistr.duck.dao.TokenDao;
import com.magistr.duck.entity.Token;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import java.util.List;
import java.util.Optional;

public class TokenMybatisDao extends SqlSessionDaoSupport implements TokenDao {

    private static final String MAPPER_NAMESPACE = "com.magistr.duck.dao.TokenDao";

    @Override
    public void create(Token token) {
        getSqlSession().insert(MAPPER_NAMESPACE + ".create", token);
    }

    @Override
    public Optional<Token> read(String hash) {
        return Optional.ofNullable(getSqlSession().selectOne(MAPPER_NAMESPACE + ".read", hash));
    }

    @Override
    public void update(Token token) {
        getSqlSession().update(MAPPER_NAMESPACE + ".update", token);
    }

    @Override
    public void delete(String hash) {
        getSqlSession().delete(MAPPER_NAMESPACE + ".delete", hash);
    }

    @Override
    public void deleteOverTime() {
        getSqlSession().delete(MAPPER_NAMESPACE + ".deleteOverTime");
    }

    @Override
    public List<Token> findAll() {
        return getSqlSession().selectList(MAPPER_NAMESPACE + ".findAll");
    }
}
