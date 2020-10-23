package com.example.noteapp;


public class Task {

    private String  text;
    private String  userId;
    private String  time;
    private String  date;
    private boolean completed;

    public Task(String text, boolean b, String userId) {
    }

    public Task(String text, boolean completed, String userId, String time, String date) {
        this.text       = text;
        this.completed  = completed;
        this.userId     = userId;
        this.time       = time;
        this.date       = date;
    }

    public String getTime() {
       return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Task{" +
                "text='" + text + '\'' +
                ", userId='" + userId + '\'' +
                ", time='" + time + '\'' +
                ", date='" + date + '\'' +
                ", completed=" + completed +
                '}';
    }
}
