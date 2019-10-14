package com.magistr.duck.dao;

import com.magistr.duck.entity.Token;

import java.util.List;

public interface TokenDao extends CrudDao<String, Token> {

    void deleteOverTime();

    List<Token> findAll();
}
