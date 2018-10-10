package com.magistr.duck.entity;

import java.io.Serializable;
import java.util.List;

public class User extends Entity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private String password;
    private List<Role> roles;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
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

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("User [id=").append(getId()).append(", name=").append(name).append(", password=")
                .append(password).append(", roles=").append(roles).append("]");
        return builder.toString();
    }
}
