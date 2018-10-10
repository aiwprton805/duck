package com.magistr.duck.security.impl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

import com.magistr.duck.security.SecurityService;

public class SecurityServiceImpl implements SecurityService {

    @Autowired
    private AuthenticationManager authManager;

    @Override
    public void autoLogin(HttpServletRequest request, String username, CharSequence password) {

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
        token.setDetails(new WebAuthenticationDetails(request));

        Authentication authentication = authManager.authenticate(token);

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

}
