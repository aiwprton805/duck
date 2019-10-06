package com.magistr.duck.security.impl;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.magistr.duck.entity.Role;
import com.magistr.duck.service.UserService;

public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        var optUser = userService.getUser(username);
        var user = optUser.orElseThrow(() -> new UsernameNotFoundException(username));

        Collection<GrantedAuthority> authorities = user.getRoles().stream()
                .map((Role role) -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());

        return new User(username, user.getPassword(), authorities);
    }

}
