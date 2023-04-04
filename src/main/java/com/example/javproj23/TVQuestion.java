package com.example.javproj23;

import java.io.Serializable;

public class TVQuestion extends Question implements Serializable {
    public TVQuestion(String question, String answer) {
        super("TV", question, answer);
    }
    public TVQuestion() {
        super("Music", "", "");
    }
}