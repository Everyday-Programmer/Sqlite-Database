package com.example.sqlite;

public class Note {
    String title, content;
    int id;

    public Note(String title, String content, int id) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}