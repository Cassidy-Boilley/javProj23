package com.example.javproj23;

import java.io.Serializable;

public class ComputerScienceQuestion extends Question {
    public ComputerScienceQuestion(String question, String answer) {
        super("Computer Science", question, answer);
    }
    public ComputerScienceQuestion() {
        super("Computer Science", "", "");
    }
}