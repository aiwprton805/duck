package com.magistr.duck.entity;

import java.io.Serializable;

import com.magistr.duck.common.enums.Lang;

public class Term extends Entity implements Serializable {

    private static final long serialVersionUID = 1L;

    private Lang     lang;
    private String   name;
    private Term     translation;
    private TermInfo termInfo;

    public Term() {
    }

    public Term(Lang lang, String name, Term translation, TermInfo termInfo) {
        super();
        this.lang = lang;
        this.name = name;
        this.translation = translation;
        this.termInfo = termInfo;
    }

    public Lang getLang() {
        return lang;
    }

    public String getName() {
        return name;
    }

    public Term getTranslation() {
        return translation;
    }

    public TermInfo getTermInfo() {
        return termInfo;
    }

    public void setLang(Lang lang) {
        this.lang = lang;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTranslation(Term translation) {
        this.translation = translation;
    }

    public void setTermInfo(TermInfo termInfo) {
        this.termInfo = termInfo;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Term [lang=").append(lang).append(", name=").append(name).append(", translation{ lang=")
                .append(translation.lang).append(", name=").append(translation.name).append(", termInfo=")
                .append(translation.termInfo).append("}, termInfo=").append(termInfo).append("]");
        return builder.toString();
    }

}
