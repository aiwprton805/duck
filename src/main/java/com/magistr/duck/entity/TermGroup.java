package com.magistr.duck.entity;

import java.io.Serializable;
import java.util.List;

public class TermGroup extends Entity implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<Term> terms;

    private String picturesDirectory;

    private String author;

    public TermGroup() {
    }

    public TermGroup(List<Term> terms, String picturesDirectory, String author) {
        this.terms = terms;
        this.picturesDirectory = picturesDirectory;
        this.author = author;
    }

    public List<Term> getTerms() {
        return terms;
    }

    public void setTerms(List<Term> terms) {
        this.terms = terms;
    }

    public String getPicturesDirectory() {
        return picturesDirectory;
    }

    public void setPicturesDirectory(String picturesDirectory) {
        this.picturesDirectory = picturesDirectory;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TermGroup{");
        sb.append("terms=").append(terms);
        sb.append(", picturesDirectory='").append(picturesDirectory).append('\'');
        sb.append(", author='").append(author).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
//class
