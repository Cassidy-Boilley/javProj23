package com.example.javproj23;

import java.io.Serializable;

public class MusicQuestion extends Question {
    public MusicQuestion(String question, String answer) {
        super("Music", question, answer);
    }
    public MusicQuestion() {
        super("Music", "", "");
    }
}
