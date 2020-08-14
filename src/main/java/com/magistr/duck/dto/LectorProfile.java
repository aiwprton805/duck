package com.magistr.duck.dto;

import com.magistr.duck.entity.Profile;

public class LectorProfile {

    private Profile profile;
    private Integer proposalCount;

    public LectorProfile() {
    }

    public LectorProfile(Profile profile, Integer proposalCount) {
        this.profile = profile;
        this.proposalCount = proposalCount;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public Integer getProposalCount() {
        return proposalCount;
    }

    public void setProposalCount(Integer proposalCount) {
        this.proposalCount = proposalCount;
    }

    @Override
    public String toString() {
        return "LectorProfile{" +
                "profile=" + profile +
                ", proposalCount=" + proposalCount +
                '}';
    }
}
//class
