package com.example.contacts.database;

import androidx.room.RoomDatabase;

import com.example.contacts.models.Contact;

@androidx.room.Database(entities = {Contact.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ContactDao getContactDao();
}
