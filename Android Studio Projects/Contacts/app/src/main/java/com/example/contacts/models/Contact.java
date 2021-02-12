package com.example.contacts.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Contact {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "name")
    public String name = "";

    @ColumnInfo(name = "email")
    public String email = "";

    @ColumnInfo(name = "phone_number")
    public String phoneNumber = "";

    @ColumnInfo(name = "is_complete")
    public boolean isComplete;

    @ColumnInfo(name = "image_uri")
    public String imageUri = "";
}
