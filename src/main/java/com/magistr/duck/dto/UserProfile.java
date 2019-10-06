package com.magistr.duck.dto;

import com.magistr.duck.entity.*;

import java.io.Serializable;
import java.util.List;

public class UserProfile implements Serializable {

    private Integer userId;
    private String username;
    private String password;
    private List<Role> roles;

    private Integer profileId;
    private String name;
    private String email;

    public UserProfile() {
    }

    public UserProfile(Integer userId, String username, String password, List<Role> roles, Integer profileId, String name, String email) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.profileId = profileId;
        this.name = name;
        this.email = email;
    }

    public UserProfile(User user, Profile profile){
        this.userId = user.getId();
        this.username = user.getName();
        this.password = user.getPassword();
        this.roles = user.getRoles();
        this.profileId = profile.getId();
        this.name = profile.getName();
        this.email = profile.getEmail();
    }

    public User toUser(){
        var user =  new User(username, password, roles);
        user.setId(userId);
        return user;
    }

    public Profile toProfile(){
        var profile = new Profile(userId, name, email);
        profile.setId(profileId);
        return profile;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public Integer getProfileId() {
        return profileId;
    }

    public void setProfileId(Integer profileId) {
        this.profileId = profileId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "UserProfile{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", roles=" + roles +
                ", profileId=" + profileId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
