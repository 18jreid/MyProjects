package com.example.roomexample.presenters;

import androidx.room.Room;

import com.example.roomexample.database.AppDatabase;
import com.example.roomexample.models.Todo;

import java.util.ArrayList;

public class TodosPresenter {
    private MVPView view;
    private ArrayList<Todo> todos = new ArrayList<>();
    private AppDatabase database;

    public interface MVPView {
        public void renderTodos(ArrayList<Todo> todos);
        public AppDatabase getContextDatabase();
        public void goToNewTodosPage();
    }

    public TodosPresenter(MVPView view) {
        this.view = view;
        database = view.getContextDatabase();
        refreshTodos();
    }

    public void goToNewTodosPage() {
        view.goToNewTodosPage();
    }

    public void updateTodo(Todo todo) {
        new Thread(() -> {
            database.getTodoDao().update(todo);
        }).start();
    }

    public void refreshTodos() {
        new Thread(() -> {
            todos = (ArrayList<Todo>)database.getTodoDao().getAllToDos();
            view.renderTodos(todos);
        }).start();
    }
}
