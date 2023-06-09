package com.example.javproj23;

import java.io.Serializable;

public abstract class Question implements Serializable {
    private String category;
    private String question;
    private String answer;

    public Question(String category, String question, String answer) {
        this.category = category;
        this.question = question;
        this.answer = answer;
    }

    public String getCategory() {
        return category;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }
}