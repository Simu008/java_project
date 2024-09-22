package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main extends Application {

    // Simulated local question bank (can be replaced with backend later)
    private List<Question> questionBank = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) {
        // UI Components
        TextField topicField = new TextField();
        TextField difficultyField = new TextField();
        TextField numQuestionsField = new TextField();
        TextArea examArea = new TextArea();
        Button generateButton = new Button("Generate Exam");
        Button saveButton = new Button("Save Exam");

        // Layout
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));
        layout.getChildren().addAll(
                new Label("Topic:"), topicField,
                new Label("Difficulty:"), difficultyField,
                new Label("Number of Questions:"), numQuestionsField,
                generateButton, examArea, saveButton
        );

        // Button Actions
        generateButton.setOnAction(e -> {
            String topic = topicField.getText();
            String difficulty = difficultyField.getText();
            int numQuestions = Integer.parseInt(numQuestionsField.getText());
            String exam = generateExam(topic, difficulty, numQuestions);
            examArea.setText(exam);
        });

        saveButton.setOnAction(e -> {
            saveExamToFile(examArea.getText());
        });

        // Preload some mock questions into the local question bank
        preloadMockQuestions();

        // Set up the stage
        Scene scene = new Scene(layout, 400, 500);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Automated Exam Paper Generator");
        primaryStage.show();
    }

    // Simulated exam generation using local question bank
    private String generateExam(String topic, String difficulty, int numQuestions) {
        List<Question> filteredQuestions = new ArrayList<>();
        for (Question q : questionBank) {
            if (q.getTopic().equalsIgnoreCase(topic) && q.getDifficulty().equalsIgnoreCase(difficulty)) {
                filteredQuestions.add(q);
            }
        }

        // Shuffle and select the desired number of questions
        Collections.shuffle(filteredQuestions);
        List<Question> selectedQuestions = filteredQuestions.subList(0, Math.min(numQuestions, filteredQuestions.size()));

        // Build the exam as a string
        StringBuilder exam = new StringBuilder();
        int questionNumber = 1;
        for (Question question : selectedQuestions) {
            exam.append(questionNumber).append(". ").append(question.getText()).append("\n\n");
            questionNumber++;
        }
        return exam.toString();
    }

    // Method to save the exam text to a file
    private void saveExamToFile(String examText) {
        try (java.io.BufferedWriter writer = new java.io.BufferedWriter(new java.io.FileWriter("GeneratedExam.txt"))) {
            writer.write(examText);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to preload mock questions for testing
    private void preloadMockQuestions() {
        questionBank.add(new Question("What is Java?", "Programming", "easy"));
        questionBank.add(new Question("Explain OOP principles.", "Programming", "medium"));
        questionBank.add(new Question("What is polymorphism?", "Programming", "medium"));
        questionBank.add(new Question("What is inheritance?", "Programming", "easy"));
        questionBank.add(new Question("Define encapsulation.", "Programming", "hard"));
        questionBank.add(new Question("What are Java collections?", "Programming", "medium"));
        questionBank.add(new Question("Explain multithreading in Java.", "Programming", "hard"));
    }

    public static void main(String[] args) {
        launch(args);
    }
}




