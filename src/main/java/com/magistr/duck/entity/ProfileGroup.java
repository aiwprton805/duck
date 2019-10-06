package com.magistr.duck.entity;

import java.io.Serializable;
import java.util.List;

public class ProfileGroup extends Entity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private String token;
    private List<Profile> profiles;

    public ProfileGroup() {
    }

    public ProfileGroup(String name, String token, List<Profile> profiles) {
        this.name = name;
        this.token = token;
        this.profiles = profiles;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<Profile> getProfiles() {
        return profiles;
    }

    public void setProfiles(List<Profile> profiles) {
        this.profiles = profiles;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ProfileGroup{");
        sb.append("name='").append(name).append('\'');
        sb.append(", token='").append(token).append('\'');
        sb.append(", profiles=").append(profiles);
        sb.append('}');
        return sb.toString();
    }
}
//class
