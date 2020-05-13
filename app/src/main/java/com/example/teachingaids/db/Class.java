package com.example.teachingaids.db;

import com.orm.SugarRecord;

import java.util.List;


public class Class extends SugarRecord {
    public Long ClassId;
    public String ClassName;
    public String ClassContent;
    public List<Stu> stuList;
    public String Noti; //公告表
    public int position;

    public List<Stu> getStuList() {
        return stuList;
    }

    public void setStuList(List<Stu> stuList) {
        this.stuList = stuList;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public Long getId() {
        return ClassId;
    }

    public String getClassName() {
        return ClassName;
    }

    public String getClassContent() {
        return ClassContent;
    }

    public void setId(Long id) {
        this.ClassId = id;
    }

    public void setClassName(String className) {
        ClassName = className;
    }

    public void setClassContent(String classContent) {
        ClassContent = classContent;
    }

    public Class(String Name,String Content){
        this.ClassName = Name;
        this.ClassContent = Content;
    }
    public Class(){
    }

}

