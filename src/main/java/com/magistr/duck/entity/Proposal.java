package com.magistr.duck.entity;

import com.magistr.duck.common.enums.Sex;

import java.io.Serializable;

public class Proposal extends Entity implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
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

    public Proposal() {
    }

    public Proposal(Integer id, String lastName, String firstName, String patronymic, String nickname, Sex sex,
                    String email, String term, String problem, String contextPath, String contextUrl,
                    String imagesPath, String otherDocsPath) {
        this.id = id;
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
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Proposal{");
        sb.append("id=").append(id);
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", patronymic='").append(patronymic).append('\'');
        sb.append(", nickname='").append(nickname).append('\'');
        sb.append(", sex=").append(sex);
        sb.append(", email='").append(email).append('\'');
        sb.append(", term='").append(term).append('\'');
        sb.append(", problem='").append(problem).append('\'');
        sb.append(", contextPath='").append(contextPath).append('\'');
        sb.append(", contextUrl='").append(contextUrl).append('\'');
        sb.append(", imagesPath='").append(imagesPath).append('\'');
        sb.append(", otherDocsPath='").append(otherDocsPath).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
//class
