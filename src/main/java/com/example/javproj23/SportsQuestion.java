package com.example.javproj23;

import java.io.Serializable;

public class SportsQuestion extends Question {
    public SportsQuestion(String question, String answer) {
        super("Sports", question, answer);
    }
    public SportsQuestion() {
        super("Sports", "", "");
    }
}