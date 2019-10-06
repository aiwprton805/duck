package com.magistr.duck.entity;

import java.io.Serializable;

public class Comment extends Entity implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer termId;
    private String forName;
    private String forArea;
    private String forDescription;
    private String forImage;
    private String forExamples;

    public Comment(){}

    public Comment(Integer termId, String forName, String forArea, String forDescription, String forImage, String forExamples) {
        this.termId = termId;
        this.forName = forName;
        this.forArea = forArea;
        this.forDescription = forDescription;
        this.forImage = forImage;
        this.forExamples = forExamples;
    }

    public Integer getTermId() {
        return termId;
    }

    public void setTermId(Integer termId) {
        this.termId = termId;
    }

    public String getForName() {
        return forName;
    }

    public void setForName(String forName) {
        this.forName = forName;
    }

    public String getForArea() {
        return forArea;
    }

    public void setForArea(String forArea) {
        this.forArea = forArea;
    }

    public String getForDescription() {
        return forDescription;
    }

    public void setForDescription(String forDescription) {
        this.forDescription = forDescription;
    }

    public String getForImage() {
        return forImage;
    }

    public void setForImage(String forImage) {
        this.forImage = forImage;
    }

    public String getForExamples() {
        return forExamples;
    }

    public void setForExamples(String forExamples) {
        this.forExamples = forExamples;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Comment{");
        sb.append("termId=").append(termId);
        sb.append(", forName='").append(forName).append('\'');
        sb.append(", forArea='").append(forArea).append('\'');
        sb.append(", forDescription='").append(forDescription).append('\'');
        sb.append(", forImage='").append(forImage).append('\'');
        sb.append(", forExamples='").append(forExamples).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
