package com.magistr.duck.security.impl;

import com.magistr.duck.entity.User;
import com.magistr.duck.security.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class SecurityServiceImpl implements SecurityService {

    private final AuthenticationManager authManager;
    private final SessionRegistry sessionRegistry;
    private final JdbcTokenRepositoryImpl jdbcTokenRepository;

    @Autowired
    public SecurityServiceImpl(AuthenticationManager authManager, SessionRegistry sessionRegistry, JdbcTokenRepositoryImpl jdbcTokenRepository) {
        this.authManager = authManager;
        this.sessionRegistry = sessionRegistry;
        this.jdbcTokenRepository = jdbcTokenRepository;
    }

    @Override
    public void autoLogin(HttpServletRequest request, String username, CharSequence password) {

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
        token.setDetails(new WebAuthenticationDetails(request));

        Authentication authentication = authManager.authenticate(token);

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    @Override
    public void invalidateUserSession(User user) {
        sessionRegistry.getAllPrincipals().stream()
                .filter(principal -> principal instanceof org.springframework.security.core.userdetails.User &&
                        ((org.springframework.security.core.userdetails.User) principal).getUsername().equals(user.getName()))
                .findFirst()
                .ifPresent(principal -> {
                    jdbcTokenRepository.removeUserTokens(user.getName());
                    List<SessionInformation> sessions = sessionRegistry.getAllSessions(principal, false);
                    for(var session: sessions){
                        session.expireNow();
                    }
                });
    }

}
