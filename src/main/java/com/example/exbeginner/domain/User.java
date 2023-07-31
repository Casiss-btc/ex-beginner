package com.example.exbeginner.domain;

public class User {
    public String name;
    public String age;
    public String comment;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAge() {
        return age;
    }
    public void setAge(String age) {
        this.age = age;
    }
    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
    @Override
    public String toString() {
        return "User [name=" + name + ", age=" + age + ", comment=" + comment + "]";
    }
    
}
