package com.example.contacts.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.contacts.models.Contact;

import java.util.List;

@Dao
public interface ContactDao {
    @Query("SELECT * from contact ")
    List<Contact> getAllContacts();

    @Insert
    void insert(Contact contact);

    @Update
    void update(Contact contact);

    @Delete
    void delete(Contact contact);
}
