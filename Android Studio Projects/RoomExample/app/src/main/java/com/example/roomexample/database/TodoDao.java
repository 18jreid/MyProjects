package com.example.roomexample.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;

import com.example.roomexample.models.Todo;
import java.util.List;

@Dao
public interface TodoDao {


    List<Todo> getAllToDos();

    @Insert
    void insert(Todo todo);

    @Update
    void update(Todo todo);

    @Delete
    void delete(Todo todo);
}
