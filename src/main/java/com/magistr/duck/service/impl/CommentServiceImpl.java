package com.magistr.duck.service.impl;

import com.magistr.duck.dao.CommentDao;
import com.magistr.duck.entity.Comment;
import com.magistr.duck.entity.Term;
import com.magistr.duck.service.CommentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    private final static Logger LOGGER = LoggerFactory.getLogger(CommentServiceImpl.class);

    private final CommentDao commentDao;

    @Autowired
    public CommentServiceImpl(CommentDao commentDao){
        this.commentDao = commentDao;
    }

    @Override
    public Optional<Comment> getComment(Integer id) {
        return commentDao.read(id);
    }

    @Override
    public Optional<Comment> getComment(Comment comment) {
        return commentDao.read(comment.getId());
    }

    @Override
    public Optional<Comment> getComment(Term term) {
        return commentDao.findByTermId(term.getId());
    }

    @Override
    public void saveComment(Comment comment) {
        if(comment.getId() == null){
            commentDao.create(comment);
        }else {
            commentDao.update(comment);
        }
    }

    @Override
    public void removeComment(Integer id) {
        commentDao.delete(id);
    }

    @Override
    public void removeComment(Comment comment) {
        commentDao.delete(comment.getId());
    }
}
