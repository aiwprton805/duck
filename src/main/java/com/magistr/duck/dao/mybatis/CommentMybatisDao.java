package com.magistr.duck.dao.mybatis;

import com.magistr.duck.dao.CommentDao;
import com.magistr.duck.entity.Comment;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import java.util.Optional;

public class CommentMybatisDao extends SqlSessionDaoSupport implements CommentDao {

    private static final String MAPPER_NAMESPACE = "com.magistr.duck.dao.CommentDao";

    @Override
    public void create(Comment comment) {
        getSqlSession().insert(MAPPER_NAMESPACE + ".create", comment);
    }

    @Override
    public Optional<Comment> read(Integer id) {
        return Optional.ofNullable(getSqlSession().selectOne(MAPPER_NAMESPACE + ".read", id));
    }

    @Override
    public void update(Comment comment) {
        getSqlSession().update(MAPPER_NAMESPACE + ".update", comment);
    }

    @Override
    public void delete(Integer id) {
        getSqlSession().delete(MAPPER_NAMESPACE + ".delete", id);
    }

    @Override
    public Optional<Comment> findByTermId(Integer termId) {
        return Optional.ofNullable(getSqlSession().selectOne(MAPPER_NAMESPACE + ".findByTermId", termId));
    }
}
