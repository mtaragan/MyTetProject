package com.project1;

public class Album {
    private int id;
    private String title;
    private int userId;

    public Album() {}

    public Album(int id, String title, int userId) {
        this.id = id;
        this.title = title;
        this.userId = userId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public String getTitle() {
        return title;
    }
}