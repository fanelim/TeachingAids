package com.example.teachingaids.db;


public class Question {
    private String title;
    private Integer score;

    public Question() {
    }

    public Question(String title, Integer score) {
        this.title = title;
        this.score = score;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
