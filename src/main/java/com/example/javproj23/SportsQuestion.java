package com.example.javproj23;

import java.io.Serializable;

public class SportsQuestion extends Question implements Serializable {
    public SportsQuestion(String question, String answer) {
        super("Sports", question, answer);
    }
    public SportsQuestion() {
        super("Sports", "", "");
    }
}