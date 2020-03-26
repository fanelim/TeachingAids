package com.example.teachingaids.db;

import org.litepal.LitePal;
import org.litepal.crud.LitePalSupport;

import java.util.ArrayList;

public class Teacher extends LitePalSupport {
    public String TeaName;
    public String TeaPhone;
    public ArrayList<Class> classList;

    public ArrayList<Class> getClassList() {
        return classList;
    }

    public String getTeaName() {
        return TeaName;
    }

    public String getTeaPhone() {
        return TeaPhone;
    }

    public void setClassList(ArrayList<Class> classList) {
        this.classList = classList;
    }

    public void setTeaName(String teaName) {
        TeaName = teaName;
    }

    public void setTeaPhone(String teaPhone) {
        TeaPhone = teaPhone;
    }
}
