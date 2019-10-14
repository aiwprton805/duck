package com.magistr.duck.entity;

import java.io.Serializable;
import java.util.List;

public class TermInfo extends Entity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String         grammar;
    private String         purview;
    private String         description;
    private List<String>   examples;
    private TermCommonInfo commonInfo;

    public TermInfo() {
    }

    public TermInfo(String grammar, String purview, String description, List<String> examples,
            TermCommonInfo commonInfo) {
        super();
        this.grammar = grammar;
        this.purview = purview;
        this.description = description;
        this.examples = examples;
        this.commonInfo = commonInfo;
    }

    public String getGrammar() {
        return grammar;
    }

    public String getPurview() {
        return purview;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getExamples() {
        return examples;
    }

    public TermCommonInfo getCommonInfo() {
        return commonInfo;
    }

    public void setGrammar(String grammar) {
        this.grammar = grammar;
    }

    public void setPurview(String purview) {
        this.purview = purview;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setExamples(List<String> examples) {
        this.examples = examples;
    }

    public void setCommonInfo(TermCommonInfo commonInfo) {
        this.commonInfo = commonInfo;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("TermInfo [grammar=").append(grammar).append(", purview=").append(purview)
                .append(", description=").append(description).append(", examples=").append(examples)
                .append(", commonInfo=").append(commonInfo).append("]");
        return builder.toString();
    }

    public class TermCommonInfo extends Entity implements Serializable {

        private static final long serialVersionUID = 1L;

        private String picturesDirName;
        private String author;

        public TermCommonInfo() {
        }

        public TermCommonInfo(String picturesDirName, String author) {
            super();
            this.picturesDirName = picturesDirName;
            this.author = author;
        }

        public String getPicturesDirName() {
            return picturesDirName;
        }

        public String getAuthor() {
            return author;
        }

        public void setPicturesDirName(String picturesDirName) {
            this.picturesDirName = picturesDirName;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append("TermCommonInfo [picturesDirName=").append(picturesDirName).append(", author=")
                    .append(author).append("]");
            return builder.toString();
        }
    }
}
