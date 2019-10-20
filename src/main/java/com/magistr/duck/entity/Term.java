package com.magistr.duck.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import com.magistr.duck.common.enums.Lang;
import com.magistr.duck.common.enums.TermStatus;

public class Term extends Entity implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer      termGroupId;
    private Lang         lang;
    private TermStatus   status;
    private String       name;
    private String       grammar;
    private String       area;
    private String       description;
    private List<String> examples;

    public Term() {
    }

    public Term(Integer termGroupId, Lang lang, TermStatus status, String name, String grammar, String area,
                String description, List<String> examples) {
        this.termGroupId = termGroupId;
        this.lang = lang;
        this.status = status;
        this.name = name;
        this.grammar = grammar;
        this.area = area;
        this.description = description;
        this.examples = examples;
    }

    public Integer getTermGroupId() {
        return termGroupId;
    }

    public void setTermGroupId(Integer termGroupId) {
        this.termGroupId = termGroupId;
    }

    public Lang getLang() {
        return lang;
    }

    public void setLang(Lang lang) {
        this.lang = lang;
    }

    public TermStatus getStatus() {
        return status;
    }

    public void setStatus(TermStatus status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGrammar() {
        return grammar;
    }

    public void setGrammar(String grammar) {
        this.grammar = grammar;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getExamples() {
        return examples;
    }

    public void setExamples(List<String> examples) {
        this.examples = examples;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Term term = (Term) o;
        return Objects.equals(getId(), term.getId()) &&
                Objects.equals(termGroupId, term.termGroupId) &&
                lang == term.lang &&
                status == term.status &&
                Objects.equals(name, term.name) &&
                Objects.equals(grammar, term.grammar) &&
                Objects.equals(area, term.area) &&
                Objects.equals(description, term.description) &&
                Objects.equals(examples, term.examples);
    }

    @Override
    public int hashCode() {
        return Objects.hash(termGroupId, lang, status, name, grammar, area, description, examples);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Term{");
        sb.append("termGroupId=").append(termGroupId);
        sb.append(", lang=").append(lang);
        sb.append(", status=").append(status);
        sb.append(", name='").append(name).append('\'');
        sb.append(", grammar='").append(grammar).append('\'');
        sb.append(", area='").append(area).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", examples=").append(examples);
        sb.append('}');
        return sb.toString();
    }

    public static class Builder{
        private final Integer      termGroupId;
        private final Lang         lang;
        private final TermStatus   status;
        private final String       name;
        private String       grammar;
        private String       area;
        private String       description;
        private List<String> examples;

        public Builder(Integer termGroupId, Lang lang, TermStatus status, String name){
            this.termGroupId = termGroupId;
            this.lang = lang;
            this.status = status;
            this.name = name;
        }

        public Builder setGrammar(String grammar){
            this.grammar = grammar;
            return this;
        }

        public Builder setArea(String area){
            this.area = area;
            return this;
        }

        public Builder setDescription(String description){
            this.description = description;
            return this;
        }

        public Builder setExamples(List<String> examples){
            this.examples = examples;
            return this;
        }

        public Term build(){
            return new Term(this);
        }
    }
    //class

    private Term(Builder builder){
        this.termGroupId = builder.termGroupId;
        this.lang = builder.lang;
        this.status = builder.status;
        this.name = builder.name;
        this.grammar = builder.grammar;
        this.area = builder.area;
        this.description = builder.description;
        this.examples = builder.examples;
    }
}
//class
