package com.example.javproj23;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

public class TriviaGame extends Application {
    private ArrayList<Question> questions = new ArrayList<>();
    private ObservableList<String> categories = FXCollections.observableArrayList("Music", "TV",
            "Computer Science", "Video Games");
    private ListView<String> categoryListView = new ListView<>(categories);
    private ListView<Question> questionListView = new ListView<>();
    private Label questionLabel = new Label();
    private TextField answerTextField = new TextField();
    private Button submitButton = new Button("Submit");
    private Label resultLabel = new Label();
    private int scoreCount = 0;
    private String filename = "C:\\Users\\cassi\\IdeaProjects\\javProj23\\src\\main\\java\\com\\example\\javproj23\\questions.txt";

    private void saveQuestions() {
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(filename));
            outputStream.writeObject(questions);
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadQuestions() {
        File file = new File(filename);
        if (!file.exists() || file.length() == 0) {
            // The file is empty or does not exist
            return;
        }
        try {
            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file));
            questions = (ArrayList<Question>) inputStream.readObject();
            inputStream.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private ObservableList<Question> filterQuestionsByCategory(String category) {
        ObservableList<Question> filteredQuestions = FXCollections.observableArrayList();
        for (Question question : questions) {
            if (question.getCategory().equals(category)) {
                filteredQuestions.add(question);
            }
        }
        return filteredQuestions;
    }
    @Override
    public void start(Stage primaryStage) {

        questions.add(new MusicQuestion("Who is the lead singer of Queen?", "Freddie Mercury"));
        questions.add(new MusicQuestion("What is the name of Taylor Swift's first album?", "Taylor Swift"));
        questions.add(new MusicQuestion("What was Elvis Presley's middle name?", "Aaron"));
        questions.add(new TVQuestion("What is the name of the family in The Simpsons?", "Simpson"));
        questions.add(new TVQuestion("What is the name of the bar in Cheers?", "Cheers"));
        questions.add(new TVQuestion("What is the name of the character played by Steve Carell in The Office (US)?", "Michael Scott"));
        questions.add(new ComputerScienceQuestion("What is the most popular programming language?", "Java"));
        questions.add(new ComputerScienceQuestion("What does HTML stand for?", "Hypertext Markup Language"));
        questions.add(new ComputerScienceQuestion("What is the name of Apple's mobile operating system?", "iOS"));
        questions.add(new VideoGamesQuestion("What favorite game?", "D2"));
        questions.add(new VideoGamesQuestion("What go yahoo?", "Mario"));
        questions.add(new VideoGamesQuestion("What BOGOS BINTED", "XBOX"));

        loadQuestions();

        // Set up the GUI
        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.setSpacing(5);

        Label categoryLabel = new Label("Select a category:");
        root.getChildren().add(categoryLabel);
        root.getChildren().add(categoryListView);

        Label questionListLabel = new Label("Select a question:");
        root.getChildren().add(questionListLabel);
        root.getChildren().add(questionListView);

        questionLabel.setWrapText(true);
        root.getChildren().add(questionLabel);

        root.getChildren().add(answerTextField);

        submitButton.setOnAction(event -> {
            String answer = answerTextField.getText();
            if (!answer.isEmpty() && !questionListView.getSelectionModel().isEmpty()) {
                Question question = questionListView.getSelectionModel().getSelectedItem();
                if (answer.equalsIgnoreCase(question.getAnswer())) {
                    resultLabel.setText("Correct!");
                    scoreCount++;
                    questions.remove(question); // Remove the question from the list
                    questionListView.setItems(filterQuestionsByCategory(categoryListView.getSelectionModel().getSelectedItem())); // Update the question list view
                } else {
                    resultLabel.setText("Incorrect.");
                }
                answerTextField.clear();
                questionLabel.setText(""); // Clear the question label
            }
            saveQuestions();
        });
        root.getChildren().add(submitButton);

        root.getChildren().add(resultLabel);

        // Set up the list views
        categoryListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            questionListView.setItems(filterQuestionsByCategory(newValue));
        });

        questionListView.setCellFactory(listView -> new ListCell<>() {
            @Override
            protected void updateItem(Question question, boolean empty) {
                super.updateItem(question, empty);
                if (empty || question == null) {
                    setText(null);
                } else {
                    setText(question.getQuestion());
                }
            }

        });

        // Create a Scene object with the root node
        Scene scene = new Scene(root, 600, 400);

        // Set the scene on the primary stage and show it
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
