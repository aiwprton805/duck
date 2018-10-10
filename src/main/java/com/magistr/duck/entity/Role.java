package com.magistr.duck.entity;

import java.io.Serializable;

public class Role extends Entity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;

    public Role() {

    }

    public Role(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Role [id=").append(getId()).append(", name=").append(name).append("]");
        return builder.toString();
    }
}
