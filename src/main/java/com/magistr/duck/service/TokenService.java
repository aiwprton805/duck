package com.magistr.duck.service;

import com.magistr.duck.entity.Token;

import java.util.List;
import java.util.Optional;

public interface TokenService {

    Token generateToken();

    Optional<Token> getToken(String hash);

    void removeToken(String hash);

    void removeOverTimeTokens();

    List<Token> getTokens();
}
