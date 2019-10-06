package com.magistr.duck.dao;

import com.magistr.duck.entity.Comment;

import java.util.Optional;

public interface CommentDao extends CrudDao<Integer, Comment> {

    Optional<Comment> findByTermId(Integer termId);
}
