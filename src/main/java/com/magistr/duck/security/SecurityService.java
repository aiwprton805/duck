package com.magistr.duck.security;

import com.magistr.duck.entity.User;

import javax.servlet.http.HttpServletRequest;

public interface SecurityService {

    void autoLogin(HttpServletRequest request, String username, CharSequence password);

    void invalidateUserSession(User user);
}
