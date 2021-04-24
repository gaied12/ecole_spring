package com.example.Ecoleback.Util;

import java.util.ArrayList;

public class ProfU {
    private String email ;
    private String firstName;

    public ArrayList<Long> getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(ArrayList<Long> subjectId) {
        this.subjectId = subjectId;
    }

    private String lastName ;
    private Long phoneNum;

    public ArrayList<Long> getLevelId() {
        return levelId;
    }

    public void setLevelId(ArrayList<Long> levelId) {
        this.levelId = levelId;
    }

    private ArrayList<Long>subjectId;
    private  ArrayList<Long>levelId;

    public Long getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(Long phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
