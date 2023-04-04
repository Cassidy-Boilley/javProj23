package com.example.javproj23;

import java.io.Serializable;

public class VideoGamesQuestion extends Question implements Serializable {
    public VideoGamesQuestion(String question, String answer) {
        super("Video Games", question, answer);
    }
    public VideoGamesQuestion() {
        super("Video Games", "", "");
    }
}
