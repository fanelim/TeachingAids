package com.example.teachingaids.db;

public class Exam {
    public String examTitle;
    public String examContent;
    public int examScore;

    public String getExamTitle() {
        return examTitle;
    }

    public String getExamContent() {
        return examContent;
    }

    public int getExamScore() {
        return examScore;
    }

    public void setExamTitle(String examTitle) {
        this.examTitle = examTitle;
    }

    public void setExamContent(String examContent) {
        this.examContent = examContent;
    }

    public void setExamScore(int examScore) {
        this.examScore = examScore;
    }
}
