package application;

public class Question {
    private String text;
    private String topic;
    private String difficulty;

    public Question(String text, String topic, String difficulty) {
        this.text = text;
        this.topic = topic;
        this.difficulty = difficulty;
    }

    public String getText() {
        return text;
    }

    public String getTopic() {
        return topic;
    }

    public String getDifficulty() {
        return difficulty;
    }
}