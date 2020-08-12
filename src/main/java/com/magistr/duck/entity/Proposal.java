package com.magistr.duck.entity;

import com.magistr.duck.common.enums.ProposalStatus;
import com.magistr.duck.common.enums.Sex;

import java.io.Serializable;
import java.time.OffsetDateTime;

public class Proposal extends Entity implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer lectorId;
    private ProposalStatus status;
    private String lastName;
    private String firstName;
    private String patronymic;
    private String nickname;
    private Sex sex;
    private String email;
    private String term;
    private String problem;
    private String contextPath;
    private String contextUrl;
    private String imagesPath;
    private String otherDocsPath;
    private OffsetDateTime created;

    public Proposal() {
    }

    public Proposal(Integer lectorId, ProposalStatus status, String lastName, String firstName, String patronymic, String nickname, Sex sex, String email, String term, String problem, String contextPath, String contextUrl, String imagesPath, String otherDocsPath, OffsetDateTime created) {
        this.lectorId = lectorId;
        this.status = status;
        this.lastName = lastName;
        this.firstName = firstName;
        this.patronymic = patronymic;
        this.nickname = nickname;
        this.sex = sex;
        this.email = email;
        this.term = term;
        this.problem = problem;
        this.contextPath = contextPath;
        this.contextUrl = contextUrl;
        this.imagesPath = imagesPath;
        this.otherDocsPath = otherDocsPath;
        this.created = created;
    }

    public Integer getLectorId() {
        return lectorId;
    }

    public void setLectorId(Integer lectorId) {
        this.lectorId = lectorId;
    }

    public ProposalStatus getStatus() {
        return status;
    }

    public void setStatus(ProposalStatus status) {
        this.status = status;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public String getContextPath() {
        return contextPath;
    }

    public void setContextPath(String contextPath) {
        this.contextPath = contextPath;
    }

    public String getContextUrl() {
        return contextUrl;
    }

    public void setContextUrl(String contextUrl) {
        this.contextUrl = contextUrl;
    }

    public String getImagesPath() {
        return imagesPath;
    }

    public void setImagesPath(String imagesPath) {
        this.imagesPath = imagesPath;
    }

    public String getOtherDocsPath() {
        return otherDocsPath;
    }

    public void setOtherDocsPath(String otherDocsPath) {
        this.otherDocsPath = otherDocsPath;
    }

    public OffsetDateTime getCreated() {
        return created;
    }

    public void setCreated(OffsetDateTime created) {
        this.created = created;
    }

    @Override
    public String toString() {
        return "Proposal{" +
                "id=" + getId() +
                ", lectorId=" + lectorId +
                ", status=" + status +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", nickname='" + nickname + '\'' +
                ", sex=" + sex +
                ", email='" + email + '\'' +
                ", term='" + term + '\'' +
                ", problem='" + problem + '\'' +
                ", contextPath='" + contextPath + '\'' +
                ", contextUrl='" + contextUrl + '\'' +
                ", imagesPath='" + imagesPath + '\'' +
                ", otherDocsPath='" + otherDocsPath + '\'' +
                ", created=" + created +
                '}';
    }
}
//class
