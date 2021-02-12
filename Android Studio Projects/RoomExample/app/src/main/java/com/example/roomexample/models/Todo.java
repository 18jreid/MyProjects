package com.example.roomexample.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Todo {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "contents")
    public String contents;

    @ColumnInfo(name = "is_complete")
    public boolean isComplete;
}
