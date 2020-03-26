package com.example.teachingaids.db;

import com.orm.SugarRecord;

import java.util.ArrayList;

public class Stu extends SugarRecord {
    public Long StuId;
    public String StuName;
    public String StuPhone;
    public int taskgrade;
    public String infoId; //解析的个人数据，需要gson
    public ArrayList<Class> classList;
    public Long ClassId;
    public int signcount;

    public int getSigncount() {
        return signcount;
    }

    public void setSigncount(int signcount) {
        this.signcount = signcount;
    }

    public int getTaskgrade() {
        return taskgrade;
    }

    public void setTaskgrade(int taskgrade) {
        this.taskgrade = taskgrade;
    }

    public void setStuId(Long stuId) {
        StuId = stuId;
    }

    public ArrayList<Class> getClassList() {
        return classList;
    }

    public void setClassList(ArrayList<Class> classList) {
        this.classList = classList;
    }

    public String getStuPhone() {
        return StuPhone;
    }

    public void setStuPhone(String stuPhone) {
        StuPhone = stuPhone;
    }

    public Long getStuId() {
        return StuId;
    }

    public Long getClassId() {
        return ClassId;
    }

    public String getInfoId() {
        return infoId;
    }

    public String getStuName() {
        return StuName;
    }

    public void setId(Long id) {
        this.StuId = id;
    }

    public void setClassId(Long classId) {
        ClassId = classId;
    }

    public void setInfoId(String  infoId) {
        this.infoId = infoId;
    }

    public void setStuName(String stuName) {
        StuName = stuName;
    }
}
