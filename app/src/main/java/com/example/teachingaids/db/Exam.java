package com.example.teachingaids.db;

public class Exam {
    public String examTitle;
    public String examContent;
    public int examScore;
    public Long exam_classid;

    public Long getExam_classid() {
        return exam_classid;
    }

    public void setExam_classid(Long exam_classid) {
        this.exam_classid = exam_classid;
    }

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
