package com.example.roomexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.example.roomexample.components.TodoListItem;
import com.example.roomexample.database.AppDatabase;
import com.example.roomexample.models.Todo;
import com.example.roomexample.presenters.TodosPresenter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements TodosPresenter.MVPView {
    TodosPresenter presenter;
    LinearLayout mainLayout;
    LinearLayout todosLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new TodosPresenter(this);
        mainLayout = new LinearLayout(this);
        todosLayout = new LinearLayout(this);
        ScrollView scrollView = new ScrollView(this);

        mainLayout.setOrientation(LinearLayout.VERTICAL);
        todosLayout.setOrientation(LinearLayout.VERTICAL);
        mainLayout.addView(todosLayout);
        scrollView.addView(mainLayout);

        AppCompatButton createNewTodoButton = new AppCompatButton(this);
        createNewTodoButton.setText("+ Create New Todo");
        createNewTodoButton.setOnClickListener(view -> {
            presenter.goToNewTodosPage();
        });
        mainLayout.addView(createNewTodoButton);
        setContentView(scrollView);
    }

    @Override
    public void renderTodos(ArrayList<Todo> todos) {
        runOnUiThread(() -> {
            todosLayout.removeAllViews();
            todos.forEach(todo -> {
                TodoListItem listItem = new TodoListItem(this, todo, () -> {
                    // Update todo
                    presenter.updateTodo(todo);
                });
                todosLayout.addView(listItem);
            });
        });
        // AppCompatTextView textView = new AppCompatTextView(this);
        // textView.setText("In this method I will render todos.");
        // setContentView(textView);
    }

    @Override
    public AppDatabase getContextDatabase() {
        return Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "todos-database").build();
    }

    @Override
    public void goToNewTodosPage() {
        Intent intent = new Intent(this, NewTodoActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.refreshTodos();
    }
}