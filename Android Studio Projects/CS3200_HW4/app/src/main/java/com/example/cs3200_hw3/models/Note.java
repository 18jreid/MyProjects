package com.example.cs3200_hw3.models;

import java.sql.Timestamp;

public class Note {
    private String user;
    private String title;
    private String note;
    private String stamp;

    public Note() {}

    public Note(String user, String title, String note, String stamp) {
        this.user = user;
        this.title = title;
        this.note = note;
        this.stamp = stamp;
    }

    public String getUser() {
        return user;
    }

    public String getTitle() {
        return title;
    }

    public String getNote() {
        return note;
    }

    public String getStamp() {
        return stamp;
    }
}
