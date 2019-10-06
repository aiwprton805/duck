package com.magistr.duck.service;

import com.magistr.duck.entity.Comment;
import com.magistr.duck.entity.Term;

import java.util.Optional;

public interface CommentService {

    Optional<Comment> getComment(Integer id);

    Optional<Comment> getComment(Comment comment);

    Optional<Comment> getComment(Term term);

    void saveComment(Comment comment);

    void removeComment(Integer id);

    void removeComment(Comment comment);
}
