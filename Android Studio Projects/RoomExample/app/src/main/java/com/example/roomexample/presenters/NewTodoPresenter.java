package com.example.roomexample.presenters;

import com.example.roomexample.database.AppDatabase;
import com.example.roomexample.models.Todo;

public class NewTodoPresenter {
    private MVPView view;
    private AppDatabase database;

    public interface MVPView {
        public AppDatabase getContextDatabase();
        public void goBackToTodosPage();
    }

    public NewTodoPresenter(MVPView view) {
        this.view = view;
        database = view.getContextDatabase();
    }

    public void createTodo(String contents) {
        // Check to make sure contents is not empty
        new Thread(() -> {
            Todo todo = new Todo();
            todo.contents = contents;
            todo.isComplete = false;
            database.getTodoDao().insert(todo);
            view.goBackToTodosPage();
        }).start();
    }
}
