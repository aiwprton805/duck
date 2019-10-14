package com.magistr.duck.service.impl;

import com.magistr.duck.dao.TokenDao;
import com.magistr.duck.entity.Token;
import com.magistr.duck.service.TokenService;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TokenServiceImpl implements TokenService {

    private final static Logger LOGGER = LoggerFactory.getLogger(TokenServiceImpl.class);

    private final TokenDao tokenDao;

    @Autowired
    public TokenServiceImpl(TokenDao tokenDao) {
        this.tokenDao = tokenDao;
    }

    @Override
    public Token generateToken() {
        String hash = RandomStringUtils.randomAlphanumeric(64);
        LocalDateTime duration = LocalDateTime.now().plusHours(1L);
        Token token = new Token(hash, duration);
        tokenDao.create(token);
        return token;
    }

    @Override
    public Optional<Token> getToken(String hash) {
        return tokenDao.read(hash);
    }

    @Override
    public void removeToken(String hash) {
        tokenDao.delete(hash);
    }

    @Override
    public void removeOverTimeTokens() {
        tokenDao.deleteOverTime();
    }

    @Override
    public List<Token> getTokens() {
        return tokenDao.findAll();
    }
}
