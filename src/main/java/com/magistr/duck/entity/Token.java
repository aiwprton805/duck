package com.magistr.duck.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Token implements Serializable {

    private static final long serialVersionUID = 1L;

    private String hash;
    private LocalDateTime duration;

    public Token(){}

    public Token(String hash, LocalDateTime duration) {
        this.hash = hash;
        this.duration = duration;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public LocalDateTime getDuration() {
        return duration;
    }

    public void setDuration(LocalDateTime duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Token{");
        sb.append("hash='").append(hash).append('\'');
        sb.append(", duration=").append(duration);
        sb.append('}');
        return sb.toString();
    }
}
